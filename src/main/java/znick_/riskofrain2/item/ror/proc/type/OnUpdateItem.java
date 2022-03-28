package znick_.riskofrain2.item.ror.proc.type;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;

public interface OnUpdateItem {

	/**
	 * Called when a player is updated every tick. Used to proc items that apply every tick. Classes that
	 * implement 
	 * 
	 * @param event The event used for the update
	 * @param player The player who was updated
	 * @param itemCount The amount of the item that the player has
	 */
	public abstract void procOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount);
	
	/**
	 * Returns whether or not the item should proc at the given update tick. Called each update tick so
	 * random generators can be used here. 
	 * 
	 * @param event The event used for the update
	 * @param player The player who was updated
	 * @param itemCount The amount of the item that the player has
	 * 
	 * @return true iff the item should proc.
	 */
	public abstract boolean shouldProcOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount);
}
