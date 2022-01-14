package znick_.riskofrain2.api.ror.items.list.white;

import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;

public class BustlingFungus extends RiskOfRain2Item {

	public BustlingFungus() {
		super("bustling_fungus");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.HEALING;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Heal all nearby allies after standing still for 2 seconds.";
	}

}
