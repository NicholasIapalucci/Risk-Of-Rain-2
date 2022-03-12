package znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.voiditems.pluripotentlarva;

import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.VoidItem;

public class PluripotentLarvaItem extends VoidItem {

	public PluripotentLarvaItem() {
		super("pluripotent_larva");
	}

	@Override
	public String getItemDescription() {
		return "Get a " 
				+ (EnumChatFormatting.LIGHT_PURPLE + "corrupted") 
				+ (EnumChatFormatting.GRAY + " extra life. Consumed on use.");
	}

	@Override
	public RiskOfRain2Item[] getCorruptedItems() {
		return new RiskOfRain2Item[] {(RiskOfRain2Item) RiskOfRain2Items.DIOS_BEST_FRIEND};
	}

}
