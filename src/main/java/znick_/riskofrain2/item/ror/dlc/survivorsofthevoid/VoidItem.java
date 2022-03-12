package znick_.riskofrain2.item.ror.dlc.survivorsofthevoid;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.dlc.DLC;
import znick_.riskofrain2.item.ror.dlc.DLCItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public abstract class VoidItem extends DLCItem {

	public VoidItem(String name) {
		super(name);
	}

	@Override
	public DLC getDLC() {
		return DLC.SURVIVORS_OF_THE_VOID;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.VOID;
	}
	
	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UNKNOWN;
	}
	
	@Override
	public String getDescription() {
		String[] itemNames = new String[this.getCorruptedItems().length];
		for (int i = 0; i < this.getCorruptedItems().length; i++) {
			String s = StatCollector.translateToLocal(this.getCorruptedItems()[i].getUnlocalizedName() + ".name");
			s = s.endsWith("s")? s : s + "s";
			itemNames[i] = s;
		}
		StringBuilder sb = new StringBuilder(this.getItemDescription() + (EnumChatFormatting.LIGHT_PURPLE + (" Corrupts all ")));
		for (int i = 0; i < itemNames.length; i++) {
			if (i != 0) sb.append(" ");
			sb.append(itemNames[i]);
			if (i < itemNames.length - 2) sb.append(",");
			if (i < itemNames.length - 1) sb.append("and");
		}
		sb.append(".");
		return sb.toString();
	}
	
	public abstract String getItemDescription();
	public abstract RiskOfRain2Item[] getCorruptedItems();
}
