package znick_.riskofrain2.item.ror.list.white.powerelixir;

import znick_.riskofrain2.item.ror.dlc.DLC;
import znick_.riskofrain2.item.ror.dlc.DLCItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class EmptyBottleItem extends DLCItem {

	public EmptyBottleItem() {
		super("empty_bottle");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.HEALING;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "An empty container for an Elixir. Does nothing.";
	}
	
	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public DLC getDLC() {
		return DLC.SURVIVORS_OF_THE_VOID;
	}

}
