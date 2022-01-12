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
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.net.PlayerHealPacketHandler.PlayerHealPacket;
import znick_.riskofrain2.net.RiskOfRain2Packets;
import znick_.riskofrain2.util.helper.InventoryHelper;
import znick_.riskofrain2.util.helper.MathHelper;

public class PlayerData implements IExtendedEntityProperties {

	private final static String EXT_PROP_NAME = "ExtendedPlayer";
	private final EntityPlayer player;
	
	private final Set<Buff> buffs = new HashSet<>();
	private final Map<PlayerStat, Double> stats = new HashMap<>();
	private int equipmentCooldown = 0;
	
	public PlayerData(EntityPlayer player) {
		this.player = player;	
		for (PlayerStat stat : PlayerStat.values()) this.resetStat(stat);
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(PlayerData.EXT_PROP_NAME, new PlayerData(player));
	}
	
	public static final PlayerData get(EntityPlayer player) {
		return (PlayerData) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	public void addBuff(Buff newBuff) {
		//Prevent buffs from applying twice, such as stacking speed with more speed from the same item
		for (Buff buff : this.buffs) if (buff.getClass() == newBuff.getClass()) return;
		this.buffs.add(newBuff);
		newBuff.applyEffect(this);
	}
	
	public void removeBuff(Buff buff) {
		buff.removeEffect(this);
		this.buffs.remove(buff);
	}
	
	public void removeBuff(Class<? extends Buff> buffClass) {
		for (Buff buff : this.buffs) if (buff.getClass() == buffClass) this.removeBuff(buff);
	}
	
	public Buff[] getBuffs() {
		return this.buffs.toArray(new Buff[0]);
	}
	
	public <T extends Buff> T getBuff(Class<T> buffClass) {
		for (Buff buff : this.buffs) if (buffClass.isInstance(buff)) return (T) buff;
		return null;
	}
	
	public boolean hasBuff(Class<? extends Buff> buffClass) {
		for (Buff buff : this.buffs) if (buff.getClass() == buffClass) return true;
		return false;
	}
	
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
	
	public double getStat(PlayerStat stat) {
		return this.stats.get(stat);
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

	public void playSound(String string) {
		this.getPlayer().playSound(string, 1, 1);
	}
	
	public int itemCount(Item item) {
		return InventoryHelper.amountOfItems(this.player, item);
	}

	public boolean isSprinting() {
		return this.player.isSprinting();
	}
	
	public double distanceFrom(Entity entity) {
		return MathHelper.getDistanceBetweenEntities(this.player, entity);
	}
	
	public AxisAlignedBB radialBox(double r) {
		return AxisAlignedBB.getBoundingBox(this.player.posX - r, this.player.posY - r, this.player.posZ - r, this.player.posX + r, this.player.posY + r, this.player.posZ + r);
	}

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

	public float getHealth() {
		return this.player.getHealth();
	}
	
	public void setMaxHealth(float maxHealth) {
		this.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
	}
	
	public void addToMaxHealth(float halfHearts) {
		this.setMaxHealth(halfHearts + this.getMaxHealth());
	}

	public float getMaxHealth() {
		return this.player.getMaxHealth();
	}

	public Vec3 getLookVector() {
		return this.player.getLookVec();
	}
	
	public PlayerCapabilities getCapabilities() {
		return this.player.capabilities;
	}

	public int getEquipmentCooldown() {
		return this.equipmentCooldown;
	}
	
	public void setEquipmentCooldown(int cooldown) {
		this.equipmentCooldown = cooldown;
	}

	public void tickEquipmentCooldown() {
		this.equipmentCooldown -= 1;
	}
}
