package znick_.riskofrain2.item.ror.list.white.powerelixir;

import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.ConsumedItem;
import znick_.riskofrain2.item.ror.dlc.DLC;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class EmptyBottleItem extends ConsumedItem {

	public EmptyBottleItem() {
		super("empty_bottle", RiskOfRain2Items.POWER_ELIXIR);
	}

	@Override
	public String getDescription() {
		return "An empty container for an Elixir. Does nothing.";
	}
	
	@Override
	public DLC getDLC() {
		return DLC.SURVIVORS_OF_THE_VOID;
	}

}
