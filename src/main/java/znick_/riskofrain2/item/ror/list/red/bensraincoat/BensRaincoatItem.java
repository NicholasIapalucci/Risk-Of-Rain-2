package znick_.riskofrain2.item.ror.list.red.bensraincoat;

import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class BensRaincoatItem extends RiskOfRain2Item {

	public BensRaincoatItem() {
		super("bens_raincoat");
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
		return "Become immune to all debuffs. Increase maximum health.";
	}

}
