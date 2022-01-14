package znick_.riskofrain2.api.ror.items.list.lunar;

import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;

public class BeadsOfFealty extends RiskOfRain2Item {

	public BeadsOfFealty() {
		super("beads_of_fealty");
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
		return "Seems to do nothing... " + (EnumChatFormatting.RED + "but...");
	}
}
