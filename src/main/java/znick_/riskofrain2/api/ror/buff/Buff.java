package znick_.riskofrain2.api.ror.buff;

import java.util.LinkedHashMap;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import znick_.riskofrain2.api.mc.data.EntityData;
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
	
	/**
	 * Map of buff classes and their unique IDs. Used for sending packets with buffs between
	 * server and client to synchronize the buffs that an entity has.
	 */
	private static final Map<Class<? extends Buff>, Integer> BUFF_IDS = new LinkedHashMap<>();
	
	/**The amount of the {@link #getItems() items} that the player has.*/
	private final int itemCount;
	/**The Risk Of Rain 2 items that gives this buff*/
	
	public Buff(int itemCount) {
		this.itemCount = itemCount;
		if (!BUFF_IDS.containsKey(this.getClass())) BUFF_IDS.put(this.getClass(), BUFF_IDS.size());
	}
	
	/**
	 * Returns a {@code ResourceLocation} representing the texture of the status effect icon,
	 * or {@code null} if the buff should not be displayed on screen. 
	 */
	public abstract ResourceLocation getIconTexture();
	
	/**
	 * Applies the effect to the entity.
	 * 
	 * @param entity The {@code EntityData} associated with the entity with the buff.
	 */
	public abstract void applyEffect(EntityData entity);
	
	/**Removes the effect from the entity.*/
	public abstract void removeEffect(EntityData entity);
	/**Returns the items that give this effect*/
	public abstract RiskOfRain2Item[] getItems();
	
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
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[" + this.itemCount + "]";
	}

	public int getID() {
		return BUFF_IDS.get(this.getClass());
	}

	public static Class<? extends Buff> fromID(int id) {
		for (Map.Entry<Class<? extends Buff>, Integer> buffEntry : BUFF_IDS.entrySet()) if (buffEntry.getValue() == id) return buffEntry.getKey();
		return null;
	}
	
	public boolean requiresItem() {
		return !(this instanceof DurationBuff);
	}
	
	/**
	 * Returns the side that this buff should apply and remove its effect on. Used to prevent
	 * effects from being run twice and messing up its effect. Set to server by default.
	 *  
	 * @return the side to run on, or {@code null} for both. 
	 */
	public Side getSide() {
		return Side.SERVER;
	}
}
