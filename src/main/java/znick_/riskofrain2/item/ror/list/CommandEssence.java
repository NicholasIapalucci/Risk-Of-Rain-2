package znick_.riskofrain2.item.ror.list;

import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class CommandEssence extends RiskOfRain2Item {

	private final ItemRarity rarity;
	
	public CommandEssence(ItemRarity rarity) {
		super(rarity.toString().toLowerCase() + "_command_essence");
		this.rarity = rarity;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UNKNOWN;
	}

	@Override
	public ItemRarity getRarity() {
		return this.rarity;
	}

	@Override
	public String getDescription() {
		return "What's your command?";
	}

	@Override
	public boolean isSpecial() {
		return true;
	}
}
