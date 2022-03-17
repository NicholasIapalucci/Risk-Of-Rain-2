package znick_.riskofrain2.item.ror.list.green.regeneratingscrap;

import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.consume.ConsumableItem;
import znick_.riskofrain2.item.ror.dlc.DLC;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class RegeneratingScrapItem extends RiskOfRain2Item implements ConsumableItem {

	public RegeneratingScrapItem() {
		super("regenerating_scrap");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.GREEN;
	}

	@Override
	public String getDescription() {
		return "Prioritized when used with Uncommon 3D Printers. Usable once per stage.";
	}
	
	@Override
	public void consume(AbstractEntityData player) {
		player.replaceItem(this, this.getBrokenItem());
	}
	
	@Override
	public DLC getDLC() {
		return DLC.SURVIVORS_OF_THE_VOID;
	}
	
	@Override
	public RiskOfRain2Item getBrokenItem() {
		return null;
	}

}
