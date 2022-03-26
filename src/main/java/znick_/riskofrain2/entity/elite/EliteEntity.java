package znick_.riskofrain2.entity.elite;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.mc.data.NonPlayerEntityData;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

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
	 * multiplied by {@link #getHealthMultiplier()}, and it's damage to its original multiplied by
	 * {@link #getDamageMultiplier()}. Also used to give entities their respective elite item.
	 */
	public default void onEntityCreation() {
		EntityLiving entity = (EntityLiving) this;
		entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(entity.getMaxHealth() * this.getHealthMultiplier());
		entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(entity.getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue() * this.getDamageMultiplier());
		((NonPlayerEntityData) EntityData.get(entity)).addItem(this.getEliteType().getItem());
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
