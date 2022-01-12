package znick_.riskofrain2.api.ror.items.lunar;

import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class Purity extends RiskOfRain2Item {

	public Purity() {
		super("purity");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.LUNAR;
	}

	@Override
	public String getDescription() {
		return "Reduce your skill cooldowns by 2 seconds. You are unlucky.";
	}
}
