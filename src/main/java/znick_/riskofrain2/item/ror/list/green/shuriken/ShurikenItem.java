package znick_.riskofrain2.item.ror.list.green.shuriken;

import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class ShurikenItem extends RiskOfRain2Item {

	public ShurikenItem(String name) {
		super(name);
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
		return "Activating your Primary skill also throws a shuriken. Recharges over time.";
	}

}
