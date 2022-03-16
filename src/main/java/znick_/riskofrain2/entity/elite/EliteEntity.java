package znick_.riskofrain2.entity.elite;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;

public interface EliteEntity {

	/**
	 * Returns the type of elite for this entity.
	 */
	public abstract EliteType getEliteType();
	
	/**
	 * Updates the elite entity with any special behavior for its elite. This should be called in
	 * the {@link net.minecraft.entity.Entity#onUpdate() Entity.onUpdate()} method.
	 */
	public abstract void updateEliteEntity();
	
	/**
	 * Called when the entity joins the world. Sets the entity's max health to its original health
	 * multiplied by {@link #getHealthMultiplier()}. Also used to apply effects such as setting blazing 
	 * enemies on fire and giving them fire resistance. This is only called once.
	 */
	public default void onEntityCreation() {
		EntityLiving entity = (EntityLiving) this;
		entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(entity.getMaxHealth() * this.getHealthMultiplier());
	}
	
	/**
	 * Returns how many times greater the entity's health is from normal.
	 */
	public abstract int getHealthMultiplier();
	
	/**
	 * Returns how many times more damage this elite type deals.
	 */
	public abstract int getDamageMultiplier();
}
