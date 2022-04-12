package znick_.riskofrain2.block.itemgen;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.event.rorevents.GenerateItemEvent;
import znick_.riskofrain2.event.rorevents.ObjectInteractionEvent;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

/**
 * Main abstract parent class for tile entities that can generate items. This includes blocks
 * such as 3D printers, chests, shop terminals, etc.
 * 
 * @author zNick_
 */
public abstract class TileEntityItemGenerator extends TileEntity implements ItemGenerator {

	/**Whether or not this item generator has been opened yet.*/
	private boolean isOpened = false;
	
	/**
	 * Returns whether or not this item generator has already been opened and no longer
	 * can be opened.
	 * 
	 * @return true iff this is opened.
	 */
	public boolean isOpened() {
		return this.isOpened;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagCompound compound = (NBTTagCompound) nbt.getTag("compound");
        this.isOpened = nbt.getBoolean("opened");
    }

	@Override
    public void writeToNBT(NBTTagCompound nbt) {
    	super.writeToNBT(nbt);
    	NBTTagCompound compound = new NBTTagCompound();
    	compound.setBoolean("opened", this.isOpened);
    	nbt.setTag("compound", compound);
    }
	
	/**
	 * Attempts to open this loot holder. Posts both an {@link znick_.riskofrain2.event.rorevents.ObjectInteractionEvent
	 * ObjectInteractionEvent} and a {@link znick_.riskofrain2.event.rorevents.GenerateItemEvent
	 * GenerateItemEvent}. Returns false if either of these are canceled and does not open the chest. Returns
	 * true if neither are cancelled and the item is generated successfully. 
	 * 
	 * @param player The player opening the loot holder
	 * @return true iff the object was opened successfully
	 */
	public boolean open(EntityPlayer player) {

		// Run some checks
		if (!this.beforeOpened(player)) return false;
		
		// Post the object interaction event and exit if it was canceled
		if (RiskOfRain2Mod.DEBUG) System.out.println("Posting object interaction event...");
		ObjectInteractionEvent event = new ObjectInteractionEvent(player, this);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			System.out.println("Object interaction event was canceled! Exiting.");
			return false;
		}
		if (RiskOfRain2Mod.DEBUG) System.out.println("Object interaction event not canceled. Continuing...");
		
		// Play the sound effect
		RiskOfRain2Item generatedItem = this.generateItem(player);
		this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, this.getSoundName(generatedItem), 1, 1);
		this.isOpened = true;
		
		// Post a generate item event and exit if it was canceled
		if (RiskOfRain2Mod.DEBUG) System.out.println("Posting item generation event...");
		GenerateItemEvent genEvent = new GenerateItemEvent(this, generatedItem, player);
		if (MinecraftForge.EVENT_BUS.post(genEvent)) {
			System.out.println("Item generation event was canceled! Exiting.");
			return false;
		}
		if (RiskOfRain2Mod.DEBUG) System.out.println("Item generation event not canceled. Continuing...");
				
		// Refresh the item to drop in case an event listener changed it
		RiskOfRain2Item toDrop = genEvent.getItem();
		
		// Create the item to drop
		EntityItem entityItem = new EntityItem(this.worldObj, ((double) this.xCoord) + 0.5, this.yCoord + 1.2, ((double) this.zCoord) + 0.5, new ItemStack(toDrop));
		entityItem.setVelocity(0, 0.4, 0.1);
		this.worldObj.spawnEntityInWorld(entityItem);
				
		this.afterOpened(player);
		
		return true;
	}
	
	/**
	 * Returns a string representing the name of the sound to play when generating an item of the given rarity.
	 * 
	 * @param item The item to get the sound name of
	 * 
	 * @return a string starting with {@code "ror2:item_spawn_"}
	 */
	private String getSoundName(RiskOfRain2Item item) {
		return RiskOfRain2Mod.MODID + ":item_spawn_" + item.getRarity().name().toLowerCase();
	}
	
	/**
	 * Called after the loot holder is opened. Used by subclasses to add custom behavior. This
	 * method is called after the item generator has been opened, and thus when this is overriden,
	 * any logic in the method can safely assume that the opening process has completed fully and 
	 * the item generator has fully and actually opened.
	 * 
	 * @see #beforeOpened(EntityPlayer)
	 */
	protected void afterOpened(EntityPlayer player) {}

	/**
	 * Called before the item generator is attempted to be opened. Note that the opening process may not be
	 * fulfilled as it could be canceled, so do not rely on the generator actually opening. 
	 * <br><br>
	 * For example, in a 3D printer, the printer does not consume a player's item here, as the 
	 * item generation could be canceled and that would result in consuming an item for nothing. Instead, 
	 * that logic is handled in {@link #afterOpened(EntityPlayer)}. 
	 * <br><br>
	 * However, the printer <i>does</i> use this method to check if the player actually has an item that 
	 * can be consumed, so that it knows not to continue if the player does not. This method is mainly for 
	 * checks, whereas actual actions are generally performed in {@link #afterOpened(EntityPlayer)}.
	 * 
	 * @param player The player who is opening this item generator
	 * 
	 * @return true if the opening process should continue, false to cancel the opening
	 */
	protected boolean beforeOpened(EntityPlayer player) {
		return true;
	}
	
}
