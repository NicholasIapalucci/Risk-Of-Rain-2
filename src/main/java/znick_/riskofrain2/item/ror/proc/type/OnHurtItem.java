package znick_.riskofrain2.item.ror.proc.type;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.EntityData;

public interface OnHurtItem {
	
	
	/**
	 * Called when the player is hurt iff {@link #shouldProcOnHurt(LivingHurtEvent, EntityData, int)}
	 * returns true. Used to proc the item. 
	 * 
	 * @param event The {@code LivingHurtEvent}.
	 * @param player The player's data
	 * @param itemCount The amount of the item the player has
	 */
	public abstract void procOnHurt(LivingHurtEvent event, EntityData player, int itemCount);
	
	/**
	 * Returns whether or not the item should proc upon being hurt. This method is called every time the
	 * player is hurt, so random generation can function in this method. 
	 * 
	 * @param event The {@code LivingHurtEvent} event
	 * @param player The player's data
	 * @param itemCount The amount of the item the player has
	 * @return {@code true} iff the item should proc
	 */
	public abstract boolean shouldProcOnHurt(LivingHurtEvent event, EntityData player, int itemCount);
}
