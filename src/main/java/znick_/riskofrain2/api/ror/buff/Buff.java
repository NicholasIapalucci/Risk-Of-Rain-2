package znick_.riskofrain2.api.ror.buff;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

/**
 * A {@code Buff} represents an effect the player gets, like a potion. Some buffs do not do anything
 * when applied or removed, as they only affect events. However, they still exist to render the buff icon
 * on the player's screen. Others, however, have an effect but no icon, as none is present in the original
 * game. These can simply return {@code null} in {@link #getIconTexture()} and they will be skipped in the
 * rendering process.
 * 
 * @author zNick_
 */
public abstract class Buff {
		
	/**The amount of the {@link #item} that the player has*/
	private final int itemCount;
	/**The Risk Of Rain 2 items that gives this buff*/
	private final RiskOfRain2Item[] items;
	
	public Buff(int itemCount, RiskOfRain2Item... items) {
		this.items = items;
		this.itemCount = itemCount;
	}
	
	/**
	 * Returns a {@code ResourceLocation} representing the texture of the status effect icon,
	 * or {@code null} if the buff should not be displayed on screen. 
	 */
	public abstract ResourceLocation getIconTexture();
	
	/**Applies the effect to the entity.*/
	public abstract void applyEffect(AbstractEntityData entity);
	/**Removes the effect from the entity.*/
	public abstract void removeEffect(AbstractEntityData entity);
	
	/**Returns the item that gives this effect*/
	public RiskOfRain2Item[] getItems() {
		return this.items;
	}
	
	/**
	 * Returns whether or not this buff gives a negative effect. Used by blast shower to detect whether or 
	 * not it should be removed upon use.
	 */
	public boolean isDebuff() {
		return false;
	}
	
	/**Returns the number of items that gave the buff;*/
	public int getItemCount() {
		return this.itemCount;
	}
	
	/**
	 * Returns whether or not this is a buff that should repeatdly apply (apply every tick).
	 * Normall this logic can be achieved without a buff by just applying the affects directly
	 * in the {@link znick_.riskofrain2.item.ror.proc.type.OnUpdateItem#procOnUpdate(LivingEvent.LivingUpdateEvent, EntityLiving, int)
	 * onUpdateItem(LivingUpdateEvent, PlayerData, int)} method, however certain items (such as weeping fungus)
	 * require a buff to be rendered on screen and thus can make use of this method by handing the
	 * logic here instead.
	 * 
	 * @return true iff the buff needs to be reapplied every tick.
	 */
	public boolean shouldRepeat() {
		return false;
	}
}
