package znick_.riskofrain2.api.ror.items;

import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class Scrap extends RiskOfRain2Item {

	private final ItemRarity rarity;
	
	public Scrap(ItemRarity rarity) {
		super(rarity.toString().toLowerCase() + "_scrap");
		this.rarity = rarity;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return rarity;
	}

	@Override
	public String getDescription() {
		return "Does nothing. Prioritized when used with 3D Printers.";
	}
}
