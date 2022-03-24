package znick_.riskofrain2.item.ror.proc.type;

import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import znick_.riskofrain2.api.mc.data.EntityData;

public interface OnPickupXPItem {

	public abstract void procOnXPPickup(PlayerPickupXpEvent event, EntityData player, int itemCount);
}
