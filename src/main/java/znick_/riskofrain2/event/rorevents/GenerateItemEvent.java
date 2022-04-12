package znick_.riskofrain2.event.rorevents;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import znick_.riskofrain2.block.itemgen.TileEntityItemGenerator;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

/**
 * The {@code GenerateItemEvent} is fired when a Risk Of Rain 2 item is generated into the world. The event
 * is cancelable, and if it is canceled, the item will not generate. However, certain consequences of the
 * item being generated will still happen. For example, if a small chest is opened and the event to generate
 * the item is canceled, the small chest will still be opened and unopenable in the future. In order to fully
 * fully prevent an interactable from being opened, see 
 * {@link znick_.riskofrain2.event.rorevents.ObjectInteractionEvent ObjectInteractionEvent}. To check what
 * type of interactable generated the item, use an {@code instanceof} check on {@link #getSource()}.
 * 
 * @author zNick_
 */
@Cancelable
public class GenerateItemEvent extends PlayerEvent {

	/**The item that was generated*/
	private RiskOfRain2Item item;
	/**The tile entity that generated the item*/
	private final TileEntityItemGenerator generationSource;
	
	/**
	 * Creates a new {@code GenerateItemEvent}.
	 * 
	 * @param source The source that generated the item
	 * @param item The item that was generated
	 */
	public GenerateItemEvent(TileEntityItemGenerator tile, RiskOfRain2Item item, EntityPlayer player) {
		super(player);
		this.item = item;
		this.generationSource = tile;
	}
	
	/**
	 * Sets the item that was generated. This will replace the item in the world.
	 * 
	 * @param item The item to generate
	 */
	public void setItem(RiskOfRain2Item item) {
		this.item = item;
	}
	
	/**
	 * Gets the item that was generated.
	 */
	public RiskOfRain2Item getItem() {
		return this.item;
	}
	
	/**
	 * Returns the tile entity that generated the item.
	 */
	public TileEntityItemGenerator getSource() {
		return this.generationSource;
	}

	
}
