package znick_.riskofrain2.api.ror.items.list.white.warbanner;

import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;

public class WarbannerItem extends RiskOfRain2Item {

	public WarbannerItem() {
		super("war_banner");
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
		return "Drop a War Banner on level up. Grants allies attack and movement speed.";
	}

}
