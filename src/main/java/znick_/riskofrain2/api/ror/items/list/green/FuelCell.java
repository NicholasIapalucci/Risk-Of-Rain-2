package znick_.riskofrain2.api.ror.items.list.green;

import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;

public class FuelCell extends RiskOfRain2Item {

	public FuelCell() {
		super("fuel_cell");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.GREEN;
	}

	@Override
	public String getDescription() {
		return "Hold an additional equipment charge. Reduce equipment cooldown.";
	}
}
