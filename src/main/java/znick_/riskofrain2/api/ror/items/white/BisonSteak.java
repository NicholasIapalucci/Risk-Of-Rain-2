package znick_.riskofrain2.api.ror.items.white;

import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class BisonSteak extends RiskOfRain2Item {

	public BisonSteak() {
		super("bison_steak");
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
		return "Regenerate health after killing an enemy.";
	}
}
