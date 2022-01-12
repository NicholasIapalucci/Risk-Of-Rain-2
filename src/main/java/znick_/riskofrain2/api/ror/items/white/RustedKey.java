package znick_.riskofrain2.api.ror.items.white;

import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class RustedKey extends RiskOfRain2Item {

	public RustedKey() {
		super("rusted_key");
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
		return "Gain access to a Rusty Lockbox that contains treasure.";
	}

}
