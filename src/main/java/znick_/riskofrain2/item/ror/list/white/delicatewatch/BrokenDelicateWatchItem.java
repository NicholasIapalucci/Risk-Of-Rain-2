package znick_.riskofrain2.item.ror.list.white.delicatewatch;

import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.consume.ConsumedItem;
import znick_.riskofrain2.item.ror.dlc.DLC;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class BrokenDelicateWatchItem extends ConsumedItem {

	public BrokenDelicateWatchItem() {
		super("broken_delicate_watch", RiskOfRain2Items.DELICATE_WATCH);
	}

	@Override
	public DLC getDLC() {
		return DLC.SURVIVORS_OF_THE_VOID;
	}

	@Override
	public String getDescription() {
		return "...well, it's still right twice a day.";
	}

}
