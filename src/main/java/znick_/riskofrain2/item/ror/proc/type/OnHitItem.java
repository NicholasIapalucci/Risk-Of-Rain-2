package znick_.riskofrain2.item.ror.proc.type;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;

public interface OnHitItem {
	
	/**
	 * Called when a player attacks an entity. Used to proc on-hit items.
	 * 
	 * @param event The event used for the attack
	 * @param player The player who attacked
	 * @param hit The entity that was attacked
	 * @param count The amount of the item that the player has
	 */
	public abstract void procOnHit(LivingAttackEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount);
	
	/**
	 * Tries to proc the item by rolling the chance or checking for whatever conditions are required
	 * to proc the item. For items that simply require the player to have it in their inventory and
	 * always proc, the method should simply always return true.
	 * 
	 * @param player The player with the item
	 * @param enemy The enemy the player hit
	 * @param itemCount The amount of the item the player has. Used for formulas that calculate percentage chance
	 * based on the amount of items.
	 */
	public abstract boolean shouldProcOnHit(LivingAttackEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount);
	
}
