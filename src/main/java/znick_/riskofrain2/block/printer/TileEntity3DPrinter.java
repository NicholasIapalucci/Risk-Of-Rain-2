package znick_.riskofrain2.block.printer;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import znick_.riskofrain2.api.ror.artifact.Artifact;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.list.ScrapItem;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.util.helper.MinecraftHelper;

public class TileEntity3DPrinter extends TileEntity {

	/**The item stack of the Risk Of Rain 2 item in this printer*/
	private ItemStack item;
	/**The last tick the printer was used on. Used to determine if the printer is on cooldown at any given moment.*/
	private int lastUsedTick;
	
	/**
	 * Creates a new {@code TileEntity3DPrinter} with the given item.
	 * 
	 * @param item The Risk Of Rain 2 item for the printer to print
	 */
	public TileEntity3DPrinter(RiskOfRain2Item item) {
		if (item == null) throw new NullPointerException("3D Printer must have a non-null item");
		this.item = new ItemStack(item);

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
		return (RiskOfRain2Item) this.item.getItem();
	}
	
	/**
	 * Returns the item stack in this printer.
	 */
	public ItemStack getItemStack() {
		return this.item;
	}
	
	/**
	 * Returns the rarity of the item in this printer.
	 */
	public ItemRarity getRarity() {
		return this.getItem().getRarity();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) { // TODO: Fix printers not saving data correctly
        super.readFromNBT(nbt);
        NBTTagCompound properties = (NBTTagCompound) nbt.getTag("item");
        this.item = ItemStack.loadItemStackFromNBT(properties);
    }

	@Override
    public void writeToNBT(NBTTagCompound nbt) {
    	super.writeToNBT(nbt);
    	NBTTagCompound properties = new NBTTagCompound();
    	this.item.writeToNBT(properties);
    	nbt.setTag("item", properties);
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
	
	/**
	 * 3D Prints the item. Loops through all items in the players inventory. If it is a Risk Of Rain 2 Item and the
	 * correct rarity, adds it to a list of potential items. After all items are queried, a random one from the list
	 * is chosen and consumed, and the item is printed. 
	 * 
	 * @param player The player who is using the printer.
	 * @return whether or not the printing action was successful; Equivalent to whether or not the player has any
	 * Risk Of Rain 2 items of the correct rarity. 
	 */
	public boolean print(EntityPlayer player) {
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
			if (potentialItems.isEmpty()) return false;
			Map.Entry<Integer, RiskOfRain2Item>[] itemsArray = potentialItems.entrySet().toArray(new Map.Entry[0]);
			itemToTake = itemsArray[new Random().nextInt(itemsArray.length)];
		}

		// Consumes the item chosen
		ItemStack stack = player.inventory.getStackInSlot(itemToTake.getKey());
		if (stack.stackSize == 1) player.inventory.setInventorySlotContents(itemToTake.getKey(), null);
		else stack.stackSize--;
		
		// Drops the item in the world and puts the printer on cooldown.
		ItemStack toDrop = Artifact.COMMAND.isEnabled()? new ItemStack(Artifact.COMMAND.getEssenceFromRarity(this.getRarity())) : new ItemStack(this.item.getItem());
		MinecraftHelper.dropItemInWorld(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord, toDrop);
		this.lastUsedTick = TickHandler.server();
		return true;
	}
}
