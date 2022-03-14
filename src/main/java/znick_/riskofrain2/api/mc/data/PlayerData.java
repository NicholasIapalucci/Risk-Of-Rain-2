package znick_.riskofrain2.api.mc.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Loadout;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.list.white.topazbrooch.BarrierPacketHandler.BarrierPacket;
import znick_.riskofrain2.item.ror.list.white.warbanner.WarbannerBuff;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.net.PlayerHealPacketHandler.PlayerHealPacket;
import znick_.riskofrain2.net.RiskOfRain2Packets;
import znick_.riskofrain2.net.SoundPacketHandler;
import znick_.riskofrain2.util.helper.MathHelper;
import znick_.riskofrain2.util.helper.MinecraftHelper;

/**
 * Class for storing data relating to the player. Players are registered upon construction with the
 * {@link #register(EntityPlayer)} method, and the associated instance of {@code PlayerData} can be
 * fetched from a player at any time using {@link #get(EntityPlayer)}. Note that not all data is
 * necessarily synchronized between server and client, so calling {@link #get(EntityPlayer)} on the 
 * server vs. the client may yield different results.
 * 
 * @author zNick_
 */
public class PlayerData implements IExtendedEntityProperties {

	/**The string used to register the extended properties.*/
	private final static String EXT_PROP_NAME = "ExtendedPlayer";
	/**The player to hold the data of.*/
	private final EntityPlayer player;
	
	/**The {@code Buffs} that the player has.*/
	private final Set<Buff> buffs = new HashSet<>();
	/**A map between the {@code PlayerStats} and their values that the player has.*/
	private final Map<PlayerStat, Double> stats = new HashMap<>();
	/**The current amount of ticks left until the player can use equipment again.*/
	private int equipmentCooldown = 0;
	
	/**The set of items that the player has unlocked.o*/
	private final Set<RiskOfRain2Item> unlockedItems = new HashSet<>();
	/**The set of items that the player has found in the world.*/
	private final Set<RiskOfRain2Item> foundItems = new HashSet<>();
	
	/**
	 * Creates a new {@code PlayerData} instance for the given player.
	 * 
	 * @throws IllegalArgumentException if the player is already registered.
	 */
	private PlayerData(EntityPlayer player) {
		if (get(player) != null) throw new IllegalArgumentException("Player already registered.");
		this.player = player;
		for (PlayerStat stat : PlayerStat.values()) this.resetStat(stat);
		for (RiskOfRain2Item item : RiskOfRain2Items.ITEM_SET) if (item.getAchievement() == null) this.unlockedItems.add(item);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		int i = 0;
		for (RiskOfRain2Item item : this.unlockedItems) {
			properties.setInteger("item_" + i, Item.getIdFromItem(item));
			i++;
		}
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		int i = 0;
		while(true) {
			int id = properties.getInteger("item_" + i);
			if (id == 0) break;
			this.unlockedItems.add((RiskOfRain2Item) Item.getItemById(id));
			i++;
		}
	}
	
	/**
	 * Registers the given player with a new instance of {@code PlayerData}.
	 * @param player The player to register
	 * 
	 * @throws IllegalArgumentException if the player is already registered.
	 */
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(PlayerData.EXT_PROP_NAME, new PlayerData(player));
	}
	
	/**
	 * Returns the {@code PlayerData} instance associated with the given player.
	 * 
	 * @param player The player to fetch the data of.
	 */
	public static final PlayerData get(EntityPlayer player) {
		return (PlayerData) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	/**Returns the player associated with this {@code PlayerData} instance*/
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	/**
	 * Adds the buff to the current player. If a buff of the same class already exists on the player, it
	 * is not added. This prevents buffs from being unintentionally stacked, such as adding speed from an
	 * energy drink when the player is already getting speed from energy drinks.
	 * 
	 * @param newBuff the buff to add.
	 * 
	 * @return whether or not the buff was added.
	 */
	public boolean addBuff(Buff newBuff) {
		
		// Proc Ben's Raincoat if necessary
		if (this.hasItem(RiskOfRain2Items.BENS_RAINCOAT) && newBuff.isDebuff()) return false;
		
		// Prevent buffs from applying twice, such as stacking speed with more speed from the same item
		for (Buff buff : this.buffs) if (buff.getClass() == newBuff.getClass()) return false;
		
		// Add the buff and apply the effect
		this.buffs.add(newBuff);
		newBuff.applyEffect(this);
		
		// Mark the buff as added successfully
		return true;
	}
	
	/**
	 * Removes the given buff from the player.
	 * 
	 * @param buff The buff to remove.
	 */
	public void removeBuff(Buff buff) {
		buff.removeEffect(this);
		this.buffs.remove(buff);
	}
	
	/**
	 * Removes the buff with the given class.
	 * 
	 * @param buffClass The class of the buff to remove.
	 */
	public void removeBuff(Class<? extends Buff> buffClass) {
		// Loop through an array of the set to prevent ConcurrentModificationExceptions
		for (Buff buff : this.getBuffs()) if (buff.getClass() == buffClass) this.removeBuff(buff);
	}
	
	/**
	 * Returns all buffs the player has in an array. Modifying the array will not change the buffs the 
	 * player has.
	 */
	public Buff[] getBuffs() {
		return this.buffs.toArray(new Buff[0]);
	}
	
	/**Returns a buff on the player with the given class, or null if the player has no such buff.*/
	public <T extends Buff> T getBuff(Class<T> buffClass) {
		for (Buff buff : this.buffs) if (buffClass.isInstance(buff)) return (T) buff;
		return null;
	}
	
	/**Returns whether or not the player has a buff with the given class*/
	public boolean hasBuff(Class<? extends Buff> buffClass) {
		for (Buff buff : this.buffs) if (buff.getClass() == buffClass) return true;
		return false;
	}

	/**
	 * Removes all expired duration buffs. Also, if the player no longer has the item that gives a buff,
	 * it will remove that buff as well. This rule does not apply to certain buffs that come from blocks
	 * such as the Warbanner, as the player does not need to have the item to receive the buff. Also
	 * applies any buffs that should be repeatedly applied.
	 */
	public void updateBuffs() {
		for (Buff buff : this.getBuffs()) {
			// Remove all expired duration buffs
			if (buff instanceof DurationBuff) {
				DurationBuff db = (DurationBuff) buff;
				if (db.getStartTick() + db.getDuration() < TickHandler.server()) {
					this.removeBuff(db);
				}
			}
			
			// Repeat all repeating buffs
			if (buff.shouldRepeat()) buff.applyEffect(this);
			
			/*
			 * Remove all buffs that correspond to items the player no longer has. Skip warbanner
			 * because the player does not need to have warbanner items to receive the buff from
			 * the warbanner block. The warbanner buff thus must be a duration buff so that it
			 * can be removed if the player is not in the radius of the block. 
			 */
			if (buff instanceof WarbannerBuff) continue;
			if (this.itemCount(buff.getItem()) <= 0) this.removeBuff(buff);
		}
	}
	
	/**Removes all buffs from the player.*/
	public void clearBuffs() {
		this.buffs.clear();
	}
	
	/**
	 * Sets the current value of this stat to its current value plus the given extra value.
	 * 
	 * @param stat The {@code PlayerStat} to add to
	 * @param addition The addition factor to the stat
	 */
	public void addToStat(PlayerStat stat, double addition) {
		this.stats.put(stat, this.stats.get(stat) + addition);
	}
	
	public void multiplyStat(PlayerStat stat, double multiply) {
		this.stats.put(stat, this.stats.get(stat) * multiply);
	}
	
	/**
	 * Returns the current value of the given stat on this player.
	 * 
	 * @param stat The {@code PlayerStat} to get the value of.
	 */
	public double getStat(PlayerStat stat) {
		return this.stats.get(stat);
	}
	
	public void setStat(PlayerStat stat, double amount) {
		this.stats.put(stat, amount);
	}
	
	public void applyStat(PlayerStat stat) {
		switch(stat) {
		case MOVEMENT_SPEED_MULTIPLIER:
			this.player.capabilities.setPlayerWalkSpeed((float) (this.getStat(stat) * 0.1));
			break;
		default: return;
		}
	}
	
	/**
	 * Resets the given stat to its default value.
	 * 
	 * @param stat The stat to reset
	 */
	public void resetStat(PlayerStat stat) {
		this.stats.put(stat, stat.getDefaultValue());
	}
	
	/**
	 * Rolls a stat for success or failure. Factors in the player's {@code LUCK} stat.
	 * 
	 * @param stat The stat to roll
	 * @return true if a sucess was rolled, false otherwise.
	 */
	public boolean rollStat(PlayerStat stat) { 
		return this.rollStat(this.getStat(stat));
	}
	
	/**
	 * Rolls a percent chance for success or failure. Factors in the player's {@code LUCK} stat.
	 * 
	 * @param procChance The percent chance of a roll succeeding
	 * @return true if a sucess was rolled, false otherwise.
	 */
	public boolean rollStat(double procChance) {
		int luck = (int) this.getStat(PlayerStat.LUCK);
		double chance = Math.random();
		boolean success;
		
		/*
		 * If the player has positive luck, roll that many times. If any roll succeeds, return true.
		 * If all rolls fail, return false.
		 */
		if (luck > 0) {
			for (int i = 0; i <= luck; i++) {
				success = chance < procChance;
				if (success) return true;
			}
			return false;
		} 
		
		/*
		 * If the player has negative luck, roll that many times. If any roll fails, return false.
		 * If all rolls succeed, return true.
		 */
		else if (luck < 0) {
			for (int i = 0; i >= luck; i--) {
				success = chance < procChance;
				if (!success) return false;
			}
			return true;
		} 
		
		/*
		 * If the player has no luck (default), simply roll once and return.
		 */
		else return chance < procChance;
	}
	
	public Loadout getLoadout() {
		Optional<Survivor> survivor = Survivor.fromPlayer(this.player);
		if (survivor.isPresent()) return survivor.get().getDefaultLoadout();
		return null;
	}

	@Override
	public void init(Entity entity, World world) {}

	/**
	 * Plays a sound at the player's location with full volume. If the player is on the server, sends a packet
	 * to play the sound on the client.
	 * 
	 * @param string The name of the sound to play.
	 */
	public void playSound(String string) {
		if (this.player.worldObj.isRemote) this.getPlayer().playSound(string, 1, 1);
		else {
			IMessage packet = new SoundPacketHandler.SoundPacket(string);
			RiskOfRain2Packets.NET.sendTo(packet, (EntityPlayerMP) this.player);
		}
	}
	
	/**
	 * Returns the amount of the given item in the players inventory
	 * 
	 * @param item The item to count.
	 */
	public int itemCount(Item item) {
		return MinecraftHelper.amountOfItems(this.player, item);
	}

	/**Returns whether or not the player is sprinting.*/
	public boolean isSprinting() {
		return this.player.isSprinting();
	}
	
	/**
	 * Returns the distance between the player and the given entity
	 * 
	 * @param entity The entity to get the distance from.
	 */
	public double distanceFrom(Entity entity) {
		return this.player.getDistanceToEntity(entity);
	}
	
	/**
	 * Creates an {@code AxisAlignedBB} centered around the player's position with the given radius.
	 * 
	 * @param r The radius of the box around the player.
	 * @return The bounding box.
	 */
	public AxisAlignedBB radialBox(double r) {
		return AxisAlignedBB.getBoundingBox(this.player.posX - r, this.player.posY - r, this.player.posZ - r, this.player.posX + r, this.player.posY + r, this.player.posZ + r);
	}

	/**Returns the world object associated with the player.*/
	public World getWorld() {
		return this.player.worldObj;
	}

	/**
	 * Heals the player. If called on the client, sends a packet to the server to heal the player.
	 * 
	 * @param halfHearts The number of half of hearts to heal.
	 */
	public void heal(float halfHearts) {
		if (this.getWorld().isRemote) {
			PlayerHealPacket packet = new PlayerHealPacket(halfHearts);
			RiskOfRain2Packets.NET.sendToServer(packet);
		}
		else this.player.heal(halfHearts);
	}

	/**Returns the health of the player*/
	public float getHealth() {
		return this.player.getHealth();
	}
	
	/**
	 * Changes the max health of the player via its {@code EntityAttribute}.
	 * 
	 * @param maxHealth the new max health of the player in half-hearts.
	 */
	public void setMaxHealth(float maxHealth) {
		this.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
	}
	
	/**
	 * Adds the given amount of health to the player's max health.
	 * 
	 * @param halfHearts The amount of half-hearts to add to the player's max health.
	 */
	public void addToMaxHealth(float halfHearts) {
		this.setMaxHealth(halfHearts + this.getMaxHealth());
	}

	/**Returns the max health of the player.*/
	public float getMaxHealth() {
		return this.player.getMaxHealth();
	}

	/**Returns the players look vector.*/
	public Vec3 getLookVector() {
		return this.player.getLookVec();
	}
	
	/**Returns the player's {@code PlayerCapabilities} instance.*/
	public PlayerCapabilities getCapabilities() {
		return this.player.capabilities;
	}

	/**Returns the player's current cooldown on equipment.*/
	public int getEquipmentCooldown() {
		return this.equipmentCooldown;
	}
	
	/**
	 * Sets the equipment cooldown to the given value.
	 * 
	 * @param cooldown The new equipment cooldown.
	 * 
	 */
	public void setEquipmentCooldown(int cooldown) {
		this.equipmentCooldown = cooldown;
	}

	/**Lowers the player's equipment cooldown by 1 tick.*/
	public void tickEquipmentCooldown() {
		this.equipmentCooldown -= 1; 
	}

	/**
	 * Adds to the players barrier. Cannot exceed the player's max health amount. If you really have to
	 * do that for some reason, use reflection. 
	 * 
	 * @param barrierAmount The amount of barrier to add.
	 */
	public void addBarrier(int barrierAmount) {
		this.setBarrier(this.getBarrier() + barrierAmount);
	}

	/**
	 * Retrieves the amount of barrier the player has.
	 */
	public int getBarrier() {
		return (int) (this.getStat(PlayerStat.BARRIER) / 100d);
	}
	
	/**
	 * Returns the exact barrier amount, equivalent to 100x greater than the displayed/used barrier amount.
	 * Used for decreasing the barrier amount with natural degeneration more fluidly. 
	 */
	public int getExactBarrier() {
		return (int) this.getStat(PlayerStat.BARRIER);
	}

	public void removeBarrier(double barrierAmount) {
		this.setBarrier((this.getStat(PlayerStat.BARRIER) - (barrierAmount * 100))/100);
	}

	/**
	 * Sets the player's barrier. Sends a packet to the other side to synchronize it between
	 * server and client.
	 * 
	 * @param barrier The amount of barrier for the player to have.
	 */
	public void setBarrier(double barrier) {
		this.setBarrier((int) (barrier * 100), true);
	} 
	
	public void setBarrier(int barrier, boolean sendPacket) {
		if (sendPacket) {
			IMessage packet = new BarrierPacket(barrier);
			if (this.getWorld().isRemote) RiskOfRain2Packets.NET.sendToServer(packet);
			else RiskOfRain2Packets.NET.sendTo(packet, (EntityPlayerMP) this.player);
		}
		
		this.setBarrierManual(barrier);
	} 
	
	private void setBarrierManual(int barrier) {
		this.setStat(PlayerStat.BARRIER, (int) MathHelper.constrain(barrier, 0, this.getMaxHealth() * 100));
	}
	
	/**
	 * Handles barrier degeneration by decreasing the barrier by 1/100. Should be called every tick.
	 */
	public void degenBarrier() {
		this.removeBarrier(0.1);
	}
	
	public void unlock(RiskOfRain2Item item) {
		this.unlockedItems.add(item);
	}
	
	/**
	 * Returns whether or not the player has unlocked the given item.
	 * 
	 * @param item The item to check
	 * @return true iff the player has unlocked the item
	 */
	public boolean hasUnlocked(RiskOfRain2Item item) {
		return this.unlockedItems.contains(item);
	}

	/**
	 * Returns an array of the items that the player has unlocked. Note that this does not mean that
	 * they have been <i>found</i>, just unlocked.
	 * 
	 * @param rarity the rarity of the unlocked items to get
	 * @return An array of items of the given rarity that the player has unlocked
	 */
	public RiskOfRain2Item[] getUnlockedItems(ItemRarity rarity) {
		return this.unlockedItems.stream().filter(item -> item.getRarity() == rarity).toArray(RiskOfRain2Item[]::new);
	}
	
	/**
	 * Returns an array of all of the items that the player has unlocked.
	 */
	public RiskOfRain2Item[] getUnlockedItems() {
		return this.unlockedItems.toArray(new RiskOfRain2Item[0]);
	}
	
	public boolean hasFound(RiskOfRain2Item item) {
		return this.foundItems.contains(item);
	}
	
	public void find(RiskOfRain2Item item) {
		this.foundItems.add(item);
	}

	/**
	 * Removes all of the given item from the player's inventory
	 * 
	 * @param item The item to remove
	 */
	public void removeAllItems(Item item) {
		ItemStack[] items = this.player.inventory.mainInventory;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getItem() == item) {
				items[i] = null;
			}
		}
	}
	
	/**
	 * Removes a single item from the players inventory. This removes one item, not all of them.
	 * For that use {@link #removeAllItems(Item)}.
	 * 
	 * @param item The item to remove
	 */
	public void removeItem(Item item) {
		ItemStack[] items = this.player.inventory.mainInventory;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getItem() == item) {
				if (items[i].stackSize > 1) items[i].stackSize--;
				else items[i] = null;
			}
		}
	}
	
	public boolean hasItem(Item item) {
		ItemStack[] items = this.player.inventory.mainInventory;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getItem() == item) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the specified amount of some item from the player's inventory.
	 * 
	 * @param item The item to remove
	 * @param count The amount of the item to remove
	 */
	public void removeItem(Item item, int count) {
		for (int i = 0; i < count; i++) this.removeItem(item);
	}
	
	public void replaceItem(Item toReplace, Item replaceWith) {
		ItemStack[] items = this.player.inventory.mainInventory;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getItem() == toReplace) {
				if (items[i].stackSize > 1) items[i].stackSize--;
				else items[i] = null;
			}
		}
		
		ItemStack stack = new ItemStack(replaceWith);
		// Try to give the player one of the new item. If it works, we're done and exit the method.
		if (this.player.inventory.addItemStackToInventory(stack)) return;
		
		//Otherwise, drop the item in the world
		EntityItem entityItem = new EntityItem(this.player.worldObj, this.player.posX, this.player.posY, this.player.posZ, stack);
		this.player.worldObj.spawnEntityInWorld(entityItem);
	}

	/**
	 * Replaces all items in the player's inventory with a different item. 
	 * 
	 * @param toReplace The item to replace
	 * @param replaceWith The item to replace it with
	 */
	public void replaceAllItems(Item toReplace, Item replaceWith) {
		ItemStack[] items = this.player.inventory.mainInventory;
		for (int i = 0; i < items.length; i++) {
			ItemStack stack = items[i];
			if (stack != null && stack.getItem() == toReplace) {
				this.player.inventory.mainInventory[i] = new ItemStack(replaceWith, stack.stackSize);
			}
		}
	}
	
	/**
	 * Returns whether or not the player is currently moving.
	 */
	public boolean isMoving() {
		return !this.isStandingStill();
	}
	
	/**
	 * Returns whether or not the player is currently standing still. Equivalent to !{@link #isMoving()}.
	 */
	public boolean isStandingStill() {
		return this.player.motionX == 0 && this.player.motionY == 0 && this.player.motionZ == 0;
	}
} 
