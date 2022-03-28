package znick_.riskofrain2.item.ror.list.green.warhorn;

import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.event.rorevents.EquipmentUsedEvent;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnEquipmentUsedItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class WarHornItem extends RiskOfRain2Item implements OnEquipmentUsedItem {

	public WarHornItem() {
		super("war_horn");
	}

	@Override
	public boolean shouldProcOnEquipmentUsed(EquipmentUsedEvent event, PlayerData player, int itemCount) {
		return true;
	}

	@Override
	public void procOnEquipmentUsed(EquipmentUsedEvent event, PlayerData player, int itemCount) {
		player.addBuff(new WarHornBuff(itemCount));
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.DAMAGE;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.GREEN;
	}

	@Override
	public String getDescription() {
		return "Activating your Equipment gives you a burst of attack speed.";
	}

}
