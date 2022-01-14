package znick_.riskofrain2.api.ror.items.list;

import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;

public class ScrapItem extends RiskOfRain2Item {

	private final ItemRarity rarity;
	
	public ScrapItem(ItemRarity rarity) {
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
