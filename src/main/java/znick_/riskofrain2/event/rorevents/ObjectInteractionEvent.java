package znick_.riskofrain2.event.rorevents;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * The {@code ObjectInteractionEvent} is fired when a player interacts with a Risk of Rain 2 
 * object. It is fired before other events such as {@code GenerateItemEvent}. Canceling this
 * will properly and fully cancel the event, and will not open chests or play sounds etc. In
 * order to check what type of object was interacted with, use an {@code instanceof} check on
 * the tile entity.
 * 
 * @author zNick_
 */
@Cancelable
public class ObjectInteractionEvent extends PlayerEvent {
	
	/**The tile entity object that was interacted with*/
	private final TileEntity object;
	
	/**
	 * Creates a new {@code ObjectInteractionEvent}.
	 * 
	 * @param player The player that interacted with the object
	 * @param object The object that was interacted with
	 */
	public ObjectInteractionEvent(EntityPlayer player, TileEntity object) {
		super(player);
		this.object = object;
	}
	
	/**
	 * Returns the tile entity object that was interacted with.
	 */
	public TileEntity getInteractedObject() {
		return this.object;
	}

}
