package znick_.riskofrain2.api.ror.items.white;

import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class TougherTimes extends RiskOfRain2Item {

	public TougherTimes() {
		super("tougher_times");
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
		return "Chance to block incoming damage.";
	}
}
