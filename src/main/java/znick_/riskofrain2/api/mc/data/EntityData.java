package znick_.riskofrain2.api.mc.data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import scala.actors.threadpool.Arrays;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.api.ror.buff.StackableBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.net.BuffPacketHandler;
import znick_.riskofrain2.net.PlayerHealPacketHandler.PlayerHealPacket;
import znick_.riskofrain2.net.PlayerStatUpdatePacketHandler.PlayerStatUpdatePacket;
import znick_.riskofrain2.net.RiskOfRain2Packets;
import znick_.riskofrain2.net.SoundPacketHandler;

/**
 * Main superclass for entity data. Entity data is broken up into two main classes: {@link PlayerData} and
 * {@link NonPlayerEntityData}. The former is for players and the latter is for other living entities. However,
 * both are subclasses of this class and this class provides the base parent functionality and framework
 * for them both. 
 * 
 * @author zNick_
 */
public abstract class EntityData<T extends EntityLivingBase> implements IExtendedEntityProperties {

	/**The player to hold the data of.*/
	protected final T entity;
	
	/**The {@code Buffs} that the player has.*/
	protected final Set<Buff> buffs = new HashSet<>();
	/**A map between the {@code PlayerStats} and their values that the player has.*/
	protected final Map<EntityStat, Double> stats = new HashMap<>();
	/**The current amount of ticks left until the player can use equipment again.*/
	protected int equipmentCooldown = 0;
	
	/**
	 * Creates a new {@code PlayerData} instance for the given player.
	 * 
	 * @throws IllegalArgumentException if the player is already registered.
	 */
	protected EntityData(T entity) {
		if (get(entity) != null) throw new IllegalArgumentException("Entity already registered.");
		this.entity = entity;
		for (EntityStat stat : EntityStat.values()) this.resetStat(stat);
	}
	
	/**
	 * Registers the given player with a new instance of {@code PlayerData}.
	 * @param player The player to register
	 * 
	 * @throws IllegalArgumentException if the player is already registered.
	 */
	public static void register(EntityLivingBase entity) {
		if (entity instanceof EntityPlayer) entity.registerExtendedProperties(PlayerData.PROPERTY_ID, new PlayerData((EntityPlayer) entity));
		else entity.registerExtendedProperties(NonPlayerEntityData.PROPERTY_ID, new NonPlayerEntityData(entity));
	}
	
	/**
	 * Returns the {@code PlayerData} instance associated with the given player.
	 * 
	 * @param player The player to fetch the data of.
	 */
	public static PlayerData get(EntityPlayer player) {
		return (PlayerData) player.getExtendedProperties(PlayerData.PROPERTY_ID);
	}
	
	public static EntityData get(EntityLivingBase entity) {
		if (entity instanceof EntityPlayer) return get((EntityPlayer) entity);
		return (NonPlayerEntityData) entity.getExtendedProperties(NonPlayerEntityData.PROPERTY_ID);
	}
	
	/**Returns the player associated with this {@code PlayerData} instance*/
	public T getEntity() {
		return this.entity;
	}
	
	/**
	 * Adds the buff to the current player.
	 * 
	 * @param newBuff the buff to add.
	 * @param repalce Whether or not to replace an exist buff the player has instead of ignoring
	 * this one.
	 * 
	 * @return whether or not the buff was added.
	 */
	public void addBuff(Buff newBuff) {
		this.addBuff(newBuff, this instanceof PlayerData);
	}
	
	public void addBuff(Buff newBuff, boolean sendPacket) {
		if (sendPacket) {
			IMessage packet = new BuffPacketHandler.BuffPacket(newBuff);
			if (this.getWorld().isRemote) RiskOfRain2Packets.NET.sendToServer(packet);
			else RiskOfRain2Packets.NET.sendTo(packet, (EntityPlayerMP) this.entity);
		}
		this.addBuffManually(newBuff);
	}
	
	private void addBuffManually(Buff buff) {
		
		//Ignore if the player already has it
		if (!(buff instanceof StackableBuff) && this.hasBuff(buff.getClass())) return;
		
		// Proc Ben's Raincoat if necessary
		if (this.hasItem(RiskOfRain2Items.BENS_RAINCOAT) && buff.isDebuff()) return;
		
		// Add the buff
		System.out.println("Adding " + buff + " on side " + this.getSide());
		this.buffs.add(buff);
		
		//If currently running on the correct side, apply the buff effect
		if (this.getSide() == buff.getSide()) buff.applyEffect(this);
	}
	
	public int getBuffCount(Class<? extends Buff> buffClass) {
		return (int) this.buffs.stream().filter(buff -> buff.getClass().equals(buffClass)).count();
	}
	
	public void removeBuff(Buff buff) {
		this.removeBuff(buff.getClass());
	}
	
	public void removeBuff(Class<? extends Buff> buffClass) {
		this.removeBuff(buffClass, this instanceof PlayerData);
	}

	public void removeBuff(Class<? extends Buff> buff, boolean sendPacket) {
		System.out.println("Removing " + buff.getSimpleName() + " on side " + this.getSide());
		if (sendPacket) {
			IMessage packet = new BuffPacketHandler.BuffPacket(buff);
			if (this.getWorld().isRemote) RiskOfRain2Packets.NET.sendToServer(packet);
			else RiskOfRain2Packets.NET.sendTo(packet, (EntityPlayerMP) this.entity);
		}
		this.removeBuffManually(buff);
	}
	
	private void removeBuffManually(Class<? extends Buff> buffClass) {
		for (Buff buff : this.getBuffs()) {
			if (buff.getClass() == buffClass) {
				if (this.getSide() == buff.getSide()) buff.removeEffect(this);
				this.buffs.remove(buff);
				return;
			}
		}
	}
	
	/**
	 * Returns all buffs the player has in an array. Modifying the array will not change the buffs the 
	 * player has.
	 */
	public Buff[] getBuffs() {
		return this.buffs.toArray(new Buff[0]);
	}
	
	/**Returns a buff on the player with the given class, or null if the player has no such buff.*/
	public <E extends Buff> E getBuff(Class<E> buffClass) {
		for (Buff buff : this.buffs) if (buffClass.isInstance(buff)) return (E) buff;
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
			
			// Remove all buffs that correspond to items the player no longer has.
			boolean hasItem = false;
			List<RiskOfRain2Item> items = Arrays.asList(buff.getItems());
			for (RiskOfRain2Item item : this.getRiskOfRain2Items().keySet()) {
				if (items.contains(item)) {
					hasItem = true;
					break;
				}
			}
			if (!buff.requiresItem()) hasItem = true;
			if (!hasItem) this.removeBuff(buff);
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
	public void addToStat(EntityStat stat, double addition) {
		this.setStat(stat, this.getStat(stat) + addition);
	}
	
	public void removeFromStat(EntityStat stat, double subtraction) {
		this.addToStat(stat, -subtraction);
	}
	
	/**
	 * Returns the current value of the given stat on this player.
	 * 
	 * @param stat The {@code PlayerStat} to get the value of.
	 */
	public double getStat(EntityStat stat) {
		return this.stats.get(stat);
	}

	public void setStat(EntityStat stat, double amount) {
		this.setStat(stat, amount, true);
	}
	
	public void setStat(EntityStat stat, double amount, boolean sendPacket) {
		if (this instanceof PlayerData && sendPacket) {
			IMessage packet = new PlayerStatUpdatePacket(stat, amount);
			if (this.getWorld().isRemote) RiskOfRain2Packets.NET.sendToServer(packet);
			else RiskOfRain2Packets.NET.sendTo(packet, (EntityPlayerMP) this.entity);
		}
		this.stats.put(stat, amount);
		this.updateStats();
	}
	
	/**
	 * Updates stats to be within acceptable range, such as ensuring shield isn't over max shield.
	 */
	private void updateStats() {
		if (this.getStat(EntityStat.SHIELD) > this.getStat(EntityStat.MAX_SHIELD)) this.setStat(EntityStat.SHIELD, this.getStat(EntityStat.MAX_SHIELD));
		if (this.getStat(EntityStat.BARRIER) > this.getMaxHealth()) this.setStat(EntityStat.BARRIER, this.getMaxHealth());
	}
	
	/**
	 * Resets the given stat to its default value.
	 * 
	 * @param stat The stat to reset
	 */
	public void resetStat(EntityStat stat) {
		this.stats.put(stat, stat.getDefaultValue());
	}
	
	/**
	 * Rolls a stat for success or failure. Factors in the player's {@code LUCK} stat.
	 * 
	 * @param stat The stat to roll
	 * @return true if a sucess was rolled, false otherwise.
	 */
	public boolean rollStat(EntityStat stat) { 
		return this.rollStat(this.getStat(stat));
	}
	
	/**
	 * Rolls a percent chance for success or failure. Factors in the player's {@code LUCK} stat.
	 * 
	 * @param procChance The percent chance of a roll succeeding
	 * @return true if a sucess was rolled, false otherwise.
	 */
	public boolean rollStat(double procChance) {
		int luck = (int) this.getStat(EntityStat.LUCK);
		double chance = Math.random();
		boolean success;
		
		/*
		 * If the player has positive luck, roll that many times. If any roll succeeds, return true.
		 * If all rolls fail, return false.
		 */
		if (luck > 0) {

		} 
		
		/*
		 * If the player has negative luck, roll that many times. If any roll fails, return false.
		 * If all rolls succeed, return true.
		 */
		else if (luck < 0) {
			
		} 
		
		/*
		 * If the player has no luck (default), simply roll once and return.
		 */
		return chance < procChance;
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
		if (this.entity.worldObj.isRemote) this.getEntity().playSound(string, 1, 1);
		else {
			IMessage packet = new SoundPacketHandler.SoundPacket(string);
			RiskOfRain2Packets.NET.sendTo(packet, (EntityPlayerMP) this.entity);
		}
	}

	/**Returns whether or not the player is sprinting.*/
	public boolean isSprinting() {
		return this.entity.isSprinting();
	}
	
	/**
	 * Returns the distance between the player and the given entity
	 * 
	 * @param entity The entity to get the distance from.
	 */
	public double distanceFrom(Entity entity) {
		return this.entity.getDistanceToEntity(entity);
	}
	
	/**
	 * Creates an {@code AxisAlignedBB} centered around the player's position with the given radius.
	 * 
	 * @param r The radius of the box around the player.
	 * @return The bounding box.
	 */
	public AxisAlignedBB radialBox(double r) {
		return AxisAlignedBB.getBoundingBox(this.entity.posX - r, this.entity.posY - r, this.entity.posZ - r, this.entity.posX + r, this.entity.posY + r, this.entity.posZ + r);
	}

	/**Returns the world object associated with the player.*/
	public World getWorld() {
		return this.entity.worldObj;
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
		else this.entity.heal(halfHearts);
	}

	/**Returns the health of the player*/
	public float getHealth() {
		return this.entity.getHealth();
	}
	
	/**
	 * Changes the max health of the player via its {@code EntityAttribute}.
	 * 
	 * @param maxHealth the new max health of the player in half-hearts.
	 */
	public void setMaxHealth(float maxHealth) {
		this.entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
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
		return this.entity.getMaxHealth();
	}

	/**Returns the players look vector.*/
	public Vec3 getLookVector() {
		return this.entity.getLookVec();
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
	 * Returns the amount of the given item in the players inventory
	 * 
	 * @param item The item to count.
	 */
	public abstract int itemCount(RiskOfRain2Item item);
	public abstract void replaceItem(RiskOfRain2Item toReplace, RiskOfRain2Item replaceWith);
	public abstract void removeItem(RiskOfRain2Item toRemove);
	public abstract boolean hasItem(RiskOfRain2Item item);
	public abstract Map<RiskOfRain2Item, Integer> getRiskOfRain2Items();
	
	public <E> Map<E, Integer> getRiskOfRain2Items(Class<E> itemType) {
		Map<RiskOfRain2Item, Integer> items = this.getRiskOfRain2Items();
		Map<E, Integer> correctItems = new HashMap<>();
		for (Map.Entry<RiskOfRain2Item, Integer> itemEntry : items.entrySet()) {
			if (itemType.isAssignableFrom(itemEntry.getKey().getClass())) correctItems.put((E) itemEntry.getKey(), itemEntry.getValue());
		}
		return correctItems;
	}

	/**
	 * Removes all of the given item from the player's inventory
	 * 
	 * @param item The item to remove
	 */
	public void removeAllItems(RiskOfRain2Item item) {
		while(this.hasItem(item)) this.removeItem(item);
	}
	
	/**
	 * Removes the specified amount of some item from the player's inventory.
	 * 
	 * @param item The item to remove
	 * @param count The amount of the item to remove
	 */
	public void removeItem(RiskOfRain2Item item, int count) {
		for (int i = 0; i < count; i++) this.removeItem(item);
	}

	/**
	 * Replaces all items in the player's inventory with a different item. 
	 * 
	 * @param toReplace The item to replace
	 * @param replaceWith The item to replace it with
	 */
	public void replaceAllItems(RiskOfRain2Item toReplace, RiskOfRain2Item replaceWith) {
		while(this.hasItem(toReplace)) this.replaceItem(toReplace, replaceWith);
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
		return this.entity.motionX == 0 && this.entity.motionY == 0 && this.entity.motionZ == 0;
	}

	public void setInvulnerable() {
		this.setInvulnerable(true);
	}
	
	public void setVulnerable() {
		this.setInvulnerable(false);
	}
	
	private void setInvulnerable(boolean vulnerable) {
		try {
			Field field = Entity.class.getDeclaredField("invulnerable");
			field.setAccessible(true);
			field.set(this.entity, vulnerable);
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Side getSide() {
		return this.getWorld().isRemote? Side.CLIENT : Side.SERVER;
	}
} 
