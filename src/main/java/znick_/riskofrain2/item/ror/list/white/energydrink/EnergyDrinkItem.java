package znick_.riskofrain2.item.ror.list.white.energydrink;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class EnergyDrinkItem extends RiskOfRain2Item implements OnUpdateItem {

	public EnergyDrinkItem() {
		super("energy_drink");
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		if (player.isSprinting()) player.addBuff(new EnergyDrinkBuff(itemCount));
		else player.removeBuff(EnergyDrinkBuff.class);
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return true;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Increase sprint speed by +25%.";
	}
}
