package znick_.riskofrain2.api.ror.items.red;

import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class HardlightAfterburner extends RiskOfRain2Item {

	public HardlightAfterburner() {
		super("hardlight_afterburner");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.RED;
	}

	@Override
	public String getDescription() {
		return "Add 2 extra charges of your Utility skill. Reduce Utility skill cooldown.";
	}
}
