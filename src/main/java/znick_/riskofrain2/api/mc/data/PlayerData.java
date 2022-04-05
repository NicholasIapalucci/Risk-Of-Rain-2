package znick_.riskofrain2.api.mc.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.data.nbt.SavableMap;
import znick_.riskofrain2.api.mc.data.nbt.SavableWith;
import znick_.riskofrain2.api.mc.data.nbt.savers.ItemSaver;
import znick_.riskofrain2.api.mc.data.nbt.savers.SurvivorSaver;
import znick_.riskofrain2.api.mc.data.nbt.savers.UUIDSaver;
import znick_.riskofrain2.api.mc.data.packets.FindItemPacketHandler.FindItemPacket;
import znick_.riskofrain2.api.ror.artifact.Artifact;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Loadout;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.net.RiskOfRain2Packets;
import znick_.riskofrain2.util.achievement.RiskOfRain2Achievements;

public class PlayerData extends AbstractEntityData<EntityPlayer> {

	public static final String PROPERTY_ID = "player_data";
	
	/**The set of items that the player has unlocked.*/
	@SavableWith(ItemSaver.class)
	private final Set<RiskOfRain2Item> unlockedItems = new HashSet<>();
	
	/**The set of items that the player has found in the world.*/
	@SavableWith(ItemSaver.class)
	private final Set<RiskOfRain2Item> foundItems = new HashSet<>();
	
	@SavableMap(keySaver = SurvivorSaver.class)
	private final Map<Survivor, Loadout> loadouts = new HashMap<>();
	
	@SavableMap(keySaver = UUIDSaver.class)
	private final Map<UUID, Object> savedObjects = new HashMap<>();
	
	private final Set<Artifact> enabledArtifacts = new HashSet<>();
	
	/**The amount of money the player has*/
	private int money;
	/**The amount of lunar coins the player has*/
	private int lunarCoins;
	/**The chance of a lunar coin being dropped by an entity. Get's halved every time one drops.*/
	private double lunarCoinChance = 0.005;
	
	protected PlayerData(EntityPlayer player) {
		super(player);
		if (RiskOfRain2Mod.DEBUG) System.out.println("Constructing PlayerData");
		
		for (RiskOfRain2Item item : RiskOfRain2Items.ITEM_SET) if (item.isUnlockedByDefault()) this.unlockedItems.add(item);
		for (Survivor survivor : Survivor.getSurvivors()) this.loadouts.put(survivor, survivor.getDefaultLoadout());
		
		enabledArtifacts.add(Artifact.COMMAND);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		System.out.println("Saving NBT data for " + this.entity.getDisplayName() + " on side " + this.getSide());
		NBTTagCompound properties = new NBTTagCompound();
		//NBTHelper.writeFieldsToNBT(properties, this);
		compound.setTag(PROPERTY_ID, properties);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		if (RiskOfRain2Mod.DEBUG) System.out.println("Loading NBT data for " + this.entity.getDisplayName() + " on side " + this.getSide());
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(PROPERTY_ID);
		//NBTHelper.readFieldsFromNBT(properties, this);
	}
	
	@Override
	public int itemCount(RiskOfRain2Item item) {
		int amount = 0;
		for (int i = 0; i < 36; i++) {
			ItemStack stackInSlot = this.entity.inventory.getStackInSlot(i);
			if (stackInSlot != null && stackInSlot.getItem() == item) {
				amount += stackInSlot.stackSize;
			}
		}
		return amount;
	}
	
	/**
	 * Unlocks the given item.
	 * 
	 * @param item The item to unlock
	 */
	public void unlock(RiskOfRain2Item item) {
		this.unlockedItems.add(item);
		this.entity.addStat(RiskOfRain2Achievements.fromItem(item), 1);
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
		this.find(item, true);
	}
	
	public void find(RiskOfRain2Item item, boolean sendPacket) {
		if (sendPacket) {
			IMessage packet = new FindItemPacket(item);
			if (this.getWorld().isRemote) RiskOfRain2Packets.NET.sendToServer(packet);
			else RiskOfRain2Packets.NET.sendTo(packet, (EntityPlayerMP) this.entity);
		}
		this.findManually(item);
	}
	
	public void findManually(RiskOfRain2Item item) {
		this.foundItems.add(item);
	}
	
	@Override
	public void replaceItem(RiskOfRain2Item toReplace, RiskOfRain2Item replaceWith) {
		ItemStack[] items = this.entity.inventory.mainInventory;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getItem() == toReplace) {
				if (items[i].stackSize > 1) items[i].stackSize--;
				else items[i] = null;
			}
		}
		
		ItemStack stack = new ItemStack(replaceWith);
		// Try to give the player one of the new item. If it works, we're done and exit the method.
		if (this.entity.inventory.addItemStackToInventory(stack)) return;
		
		//Otherwise, drop the item in the world
		EntityItem entityItem = new EntityItem(this.entity.worldObj, this.entity.posX, this.entity.posY, this.entity.posZ, stack);
		this.entity.worldObj.spawnEntityInWorld(entityItem);
	}
	
	@Override
	public void removeItem(RiskOfRain2Item item) {
		ItemStack[] items = this.entity.inventory.mainInventory;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getItem() == item) {
				if (items[i].stackSize > 1) items[i].stackSize--;
				else items[i] = null;
			}
		}
	}
	
	@Override
	public boolean hasItem(RiskOfRain2Item item) {
		ItemStack[] items = this.entity.inventory.mainInventory;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getItem() == item) {
				return true;
			}
		}
		return false;
	}

	public Loadout getLoadout() {
		if (this.isSurvivor()) return this.loadouts.get(this.getSurvivor());
		return null;
	}
	
	public boolean isSurvivor() {
		return Survivor.fromPlayer(this.entity).isPresent();
	}
	
	public Survivor getSurvivor() {
		return Survivor.fromPlayer(this.entity).get();
	}

	@Override
	public Map<RiskOfRain2Item, Integer> getRiskOfRain2Items() {
		Map<RiskOfRain2Item, Integer> itemMap = new HashMap<>();
		ItemStack[] items = this.entity.inventory.mainInventory;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getItem() instanceof RiskOfRain2Item) {
				RiskOfRain2Item riskItem = (RiskOfRain2Item) items[i].getItem();
				if (!itemMap.containsKey(riskItem)) itemMap.put(riskItem, 0);
				itemMap.put(riskItem, itemMap.get(riskItem) + items[i].stackSize);
			}
		}
		return itemMap;
	}

	public void addMoney(int money) {
		this.money += money;
	}
	
	public void consumeMoney(int money) {
		this.money = (int) MathHelper.clamp_int(money, 0, Integer.MAX_VALUE);
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void updateItems() {
		for (RiskOfRain2Item item : this.getRiskOfRain2Items().keySet()) {
			if (!this.hasFound(item)) this.find(item);
		}
	}
	
	public <E> E getSavedObject(UUID key, Class<E> eClass) {
		return (E) this.savedObjects.get(key);
	}
	
	public <E> void saveObject(UUID key, E object) {
		this.savedObjects.put(key, object);
	}

	public boolean hasArtifactEnabled(Artifact artifact) {
		return this.enabledArtifacts.contains(artifact);
	}

	public Artifact[] getEnabledArtifacts() {
		return this.enabledArtifacts.toArray(new Artifact[0]);
	}
}
