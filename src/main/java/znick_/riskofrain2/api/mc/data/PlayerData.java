package znick_.riskofrain2.api.mc.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Loadout;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class PlayerData extends EntityData<EntityPlayer> {

	public static final String PROPERTY_ID = "player_data";
	
	/**The set of items that the player has unlocked.o*/
	private final Set<RiskOfRain2Item> unlockedItems = new HashSet<>();
	/**The set of items that the player has found in the world.*/
	private final Set<RiskOfRain2Item> foundItems = new HashSet<>();
	
	/**The amount of money the player has*/
	private int money;
	/**The amount of lunar coins the player has*/
	private int lunarCoins;
	/**
	 * The chance of a lunar coin being dropped by an entity. Get's halved every time one drops. 
	 * This is not saved to NBT, so it intentionally resets when the player dies.
	 */
	private double lunarCoinChance = 0.005;
	
	private Loadout loadout = Survivor.HUNTRESS.getDefaultLoadout();
	
	protected PlayerData(EntityPlayer player) {
		super(player);
		for (RiskOfRain2Item item : RiskOfRain2Items.ITEM_SET) {
			if (item.getAchievement() == null) this.unlockedItems.add(item);
		}
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		int i = 0;
		for (RiskOfRain2Item item : this.unlockedItems) {
			properties.setInteger("item_" + i, Item.getIdFromItem(item));
			i++;
		}
		properties.setInteger("money", this.money);
		properties.setInteger("lunarCoins", this.lunarCoins);
		properties.setDouble("lunarCoinChance", this.lunarCoinChance);
		compound.setTag(PROPERTY_ID, properties);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(PROPERTY_ID);
		this.money = properties.getInteger("money");
		this.lunarCoins = properties.getInteger("lunarCoins");
		this.lunarCoinChance = properties.getDouble("lunarCoinChance");
		int i = 0;
		while(true) {
			int id = properties.getInteger("item_" + i);
			if (id == 0) break;
			this.unlockedItems.add((RiskOfRain2Item) Item.getItemById(id));
			i++;
		}
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
		return this.loadout;
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
}
