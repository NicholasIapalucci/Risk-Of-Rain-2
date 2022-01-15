package znick_.riskofrain2.api.mc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.api.ror.items.list.white.warbanner.WarbannerBuff;
import znick_.riskofrain2.api.ror.items.proc.type.OnHitItem;
import znick_.riskofrain2.event.Tick;
import znick_.riskofrain2.net.PlayerHealPacketHandler.PlayerHealPacket;
import znick_.riskofrain2.net.RiskOfRain2Packets;
import znick_.riskofrain2.util.helper.InventoryHelper;
import znick_.riskofrain2.util.helper.MathHelper;

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
	
	/**
	 * Creates a new {@code PlayerData} instance for the given player.
	 * 
	 * @throws IllegalArgumentException if the player is already registered.
	 */
	private PlayerData(EntityPlayer player) {
		if (get(player) != null) throw new IllegalArgumentException("Player already registered.");
		this.player = player;
		for (PlayerStat stat : PlayerStat.values()) this.resetStat(stat);
	}
	
	/**
	 * Registers the given player with a new instance of {@code PlayerData}.
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
		//Prevent buffs from applying twice, such as stacking speed with more speed from the same item
		for (Buff buff : this.buffs) if (buff.getClass() == newBuff.getClass()) return false;
		this.buffs.add(newBuff);
		newBuff.applyEffect(this);
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
		for (Buff buff : this.buffs) if (buff.getClass() == buffClass) this.removeBuff(buff);
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
	 * it will remove that buff as well.
	 */
	public void removeExcessBuffs() {
		for (Buff buff : this.getBuffs()) {
			//Remove all expired duration buffs
			if (buff instanceof DurationBuff) {
				DurationBuff db = (DurationBuff) buff;
				if (db.getStartTick() + db.getDuration() < Tick.server()) {
					this.removeBuff(db);
				}
			}
			
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
	
	public void removeOnHitBuffs() {
		for (Buff buff : this.getBuffs()) {
			if (buff instanceof StatBuff) {
				StatBuff sb = (StatBuff) buff;
				if (sb.getAffectedStat() == PlayerStat.DAMAGE_MULTIPLIER && buff.getItem() instanceof OnHitItem) {
					this.removeBuff(sb);
				}
			}
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
	
	/**
	 * Returns the current value of the given stat on this player.
	 * 
	 * @param stat The {@code PlayerStat} to get the value of.
	 */
	public double getStat(PlayerStat stat) {
		return this.stats.get(stat);
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
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);

	}

	@Override
	public void init(Entity entity, World world) {}

	/**
	 * Plays a sound at the player's location with full volume.
	 * 
	 * @param string The name of the sound to play.
	 */
	public void playSound(String string) {
		this.getPlayer().playSound(string, 1, 1);
	}
	
	/**
	 * Returns the amount of the given item in the players inventory
	 * 
	 * @param item The item to count.
	 */
	public int itemCount(Item item) {
		return InventoryHelper.amountOfItems(this.player, item);
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
		return MathHelper.getDistanceBetweenEntities(this.player, entity);
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
} 
