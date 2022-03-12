package znick_.riskofrain2.api.ror.buff;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
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
	
	private final int itemCount;
	private final RiskOfRain2Item item;
	
	public Buff(RiskOfRain2Item item, int itemCount) {
		this.item = item;
		this.itemCount = itemCount;
	}
	
	/**Returns a {@code ResourceLocation} representing the texture of the status effect icon.*/
	public abstract ResourceLocation getIconTexture();
	/**Applies the effect to the player.*/
	public abstract void applyEffect(PlayerData player);
	/**Removes the effect from the player.*/
	public abstract void removeEffect(PlayerData player);
	
	/**Returns the item that gives this effect*/
	public RiskOfRain2Item getItem() {
		return this.item;
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
	
	public boolean shouldRepeat() {
		return false;
	}
}
