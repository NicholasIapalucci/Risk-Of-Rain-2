package znick_.riskofrain2.item.ror.proc.type;

import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.event.rorevents.EquipmentUsedEvent;

public interface OnEquipmentUsedItem {

	public abstract boolean shouldProcOnEquipmentUsed(EquipmentUsedEvent event, PlayerData player, int itemCount);
	public abstract void procOnEquipmentUsed(EquipmentUsedEvent event, PlayerData player, int itemCount);
}
