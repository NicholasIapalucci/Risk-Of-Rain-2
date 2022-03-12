package znick_.riskofrain2.item.ror.list.white.delicatewatch;

import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.dlc.DLC;
import znick_.riskofrain2.item.ror.dlc.DLCItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class BrokenDelicateWatchItem extends DLCItem {

	public BrokenDelicateWatchItem() {
		super("broken_delicate_watch");
	}

	@Override
	public DLC getDLC() {
		return DLC.SURVIVORS_OF_THE_VOID;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UNKNOWN;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "...well, it's still right twice a day.";
	}
	
	@Override
	public boolean isSpecial() {
		return true;
	}

}
