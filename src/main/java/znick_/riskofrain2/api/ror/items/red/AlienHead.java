package znick_.riskofrain2.api.ror.items.red;

import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class AlienHead extends RiskOfRain2Item {

	public AlienHead() {
		super("alien_head");
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
		return "Reduces cooldowns for your skills.";
	}
}
