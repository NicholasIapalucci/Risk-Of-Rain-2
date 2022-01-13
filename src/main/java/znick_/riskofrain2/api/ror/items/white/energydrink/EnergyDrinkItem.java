package znick_.riskofrain2.api.ror.items.white.energydrink;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.item.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class EnergyDrinkItem extends RiskOfRain2Item implements OnUpdateItem {

	public EnergyDrinkItem() {
		super("energy_drink");
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		player.addBuff(new EnergyDrinkBuff(itemCount));
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return player.isSprinting();
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
		return "Sprint faster.";
	}
}
