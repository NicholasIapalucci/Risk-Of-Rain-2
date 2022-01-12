package znick_.riskofrain2.item.ror.proc.item;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.PlayerData;

public interface OnUpdateItem {

	/**
	 * Called when a player is updated every tick. Used to proc items that apply every tick. Classes that
	 * implement 
	 * 
	 * @param event The event used for the attack
	 * @param player The player who attacked
	 * @param itemCount The amount of the item that the player has
	 */
	public abstract void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount);
	
	public abstract boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount);
}
