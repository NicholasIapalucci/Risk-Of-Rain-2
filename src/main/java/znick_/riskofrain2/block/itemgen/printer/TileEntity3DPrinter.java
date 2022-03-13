package znick_.riskofrain2.block.itemgen.printer;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import znick_.riskofrain2.block.itemgen.TileEntityItemGenerator;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.list.ScrapItem;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class TileEntity3DPrinter extends TileEntityItemGenerator {

	/**The item stack of the Risk Of Rain 2 item in this printer*/
	private RiskOfRain2Item item;
	/**The last tick the printer was used on. Used to determine if the printer is on cooldown at any given moment.*/
	private int lastUsedTick;
	
	private EntityPlayer playerOpened = null;
	private Map.Entry<Integer, RiskOfRain2Item> itemToTake = null;
	
	/**
	 * Creates a new {@code TileEntity3DPrinter} with the given item.
	 * 
	 * @param item The Risk Of Rain 2 item for the printer to print
	 */
	public TileEntity3DPrinter(RiskOfRain2Item item) {
		if (item == null) throw new NullPointerException("3D Printer must have a non-null item");
		this.item = item;
	}
	
	/**
	 * Returns whether or not the printer was used recently. Used to prevent spamming. Note that the cooldown
	 * is not written to NBT, because I thought it unnecessary; Relogging repeatedly isn't exactly an effective
	 * spamming method.
	 */
	public boolean isOnCooldown() {
		return this.lastUsedTick + 80 > TickHandler.server();
	}

	/**
	 * Returns the item in this printer
	 */
	public RiskOfRain2Item getItem() {
		return this.item;
	}
	
	/**
	 * Returns the rarity of the item in this printer.
	 */
	public ItemRarity getRarity() {
		return this.getItem().getRarity();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.item = (RiskOfRain2Item) Item.getItemById(nbt.getInteger("item_id"));
    }

	@Override
    public void writeToNBT(NBTTagCompound nbt) {
    	super.writeToNBT(nbt);
    	nbt.setInteger("item_id", Item.getIdFromItem(this.item));
    }
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		this.readFromNBT(packet.func_148857_g());
	}
	
	public void preOpen(EntityPlayer player) {
		Map.Entry<Integer, RiskOfRain2Item> toConsume = this.getItemToConsume(player);
		boolean canPrint = toConsume != null;
		if (canPrint) {
			this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "ror2:printer", 1, 1);	
			this.playerOpened = player;
			this.consumeItem(player, toConsume);
		}
		this.lastUsedTick = TickHandler.server();
	}
	
	@Override
	public void updateEntity() {
		// Check if an item needs to be spit out
		if (this.playerOpened != null && this.lastUsedTick + 30 < TickHandler.server()) {
			boolean opened = this.open(this.playerOpened);
			// Check if the opening process failed
			if (!opened && !this.worldObj.isRemote) {
				// If it did, give the item back
				if (!this.playerOpened.inventory.addItemStackToInventory(new ItemStack(this.itemToTake.getValue()))) {
					// If the player's inventory is full and the item can't be given back, drop it in the world
					EntityItem entityItem = new EntityItem(this.playerOpened.worldObj, this.playerOpened.posX, this.playerOpened.posY, this.playerOpened.posZ, new ItemStack(this.itemToTake.getValue()));
					this.playerOpened.worldObj.spawnEntityInWorld(entityItem);
				}
			}
			// Mark this tile as no longer needing to spit an item out
			this.playerOpened = null;
		}
	}
	
	private void consumeItem(EntityPlayer player, Map.Entry<Integer, RiskOfRain2Item> entry) {
		ItemStack stack = player.inventory.getStackInSlot(entry.getKey());
		stack.stackSize--;
	}
	
	private Map.Entry<Integer, RiskOfRain2Item> getItemToConsume(EntityPlayer player) {
		
		// Map between index of inventory slots and the item to take for potential items, and what the chosen item will be
		Map<Integer, RiskOfRain2Item> potentialItems = new HashMap<>();
		Map.Entry<Integer, RiskOfRain2Item> itemToTake = null;
				
		// Loops through all of the player's inventory slots
		for (int i = 0; i < 36; i++) {
			ItemStack stackInSlot = player.inventory.getStackInSlot(i);
			if (stackInSlot != null && stackInSlot.getItem() instanceof RiskOfRain2Item) {
				RiskOfRain2Item riskItem = (RiskOfRain2Item) stackInSlot.getItem();
				// Only continues if the item is a Risk Of Rain 2 item with the correct rarity
				if (riskItem.getRarity() == this.getRarity()) {
					// Prioritizes scrap
					if (riskItem instanceof ScrapItem) {
						itemToTake = new AbstractMap.SimpleEntry<Integer, RiskOfRain2Item>(i, riskItem);
						break;
					}
					// If there is no scrap, adds the item to the set of potential items
					potentialItems.put(i, riskItem);
				}
			}
		}
				
		// If there is no scrap, picks a random item from the potential items set
		if (itemToTake == null) {
			if (potentialItems.isEmpty()) return null;
			Map.Entry<Integer, RiskOfRain2Item>[] itemsArray = potentialItems.entrySet().toArray(new Map.Entry[0]);
			itemToTake = itemsArray[new Random().nextInt(itemsArray.length)];
		}

		// Return the item to take
		return itemToTake;
	}

	@Override
	public RiskOfRain2Item generateItem(EntityPlayer player) {
		return this.item;
	}
}
