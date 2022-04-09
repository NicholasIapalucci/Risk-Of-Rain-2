package znick_.riskofrain2.event.rorevents;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

/**
 * The {@code UnlockItemEvent} is fired when the player unlocks an item. 
 * 
 * @author zNick_
 */
@Cancelable
public class UnlockItemEvent extends PlayerEvent {

	/**The item that was unlocked*/
	private RiskOfRain2Item item;
	
	/**
	 * Creates a new {@code UnlockItemEvent} 
	 * 
	 * @param player The player that unlocked the item
	 * @param item The item that was unlocked
	 */
	public UnlockItemEvent(EntityPlayer player, RiskOfRain2Item item) {
		super(player);
		this.item = item;
	}
	
	/**
	 * Returns the item that was unlocked.
	 */
	public RiskOfRain2Item getItem() {
		return this.item;
	}
	
	/**
	 * Sets the item the player unlocks.
	 * 
	 * @param item The item the player unlocks
	 */
	public void setItem(RiskOfRain2Item item) {
		if (PlayerData.get(this.entityPlayer).hasUnlocked(item)) throw new IllegalArgumentException("Cannot unlock item player has already unlocked");
		this.item = item;
	}

}
