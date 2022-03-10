package znick_.riskofrain2.event.rorevents;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * The {@code ObjectInteractionEvent} is fired when a player interacts with a Risk of Rain 2 
 * object. It is fired before other events such as {@code GenerateItemEvent}. Canceling this
 * will properly and fully cancel the event, and will not open chests or play sounds etc. 
 * 
 * @author zNick_
 */
@Cancelable
public class ObjectInteractionEvent extends PlayerEvent {
	
	private final TileEntity object;
	
	public ObjectInteractionEvent(EntityPlayer player, TileEntity object) {
		super(player);
		this.object = object;
	}
	
	public TileEntity getObject() {
		return this.object;
	}

}
