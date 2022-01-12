package znick_.riskofrain2.item.ror.proc.item;

import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import znick_.riskofrain2.api.mc.PlayerData;

public interface OnPickupXPItem {

	public abstract void procOnXPPickup(PlayerPickupXpEvent event, PlayerData player, int itemCount);
}
