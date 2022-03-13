package znick_.riskofrain2.item.ror.list.red.diosbestfriend;

import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.ConsumedItem;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class DiosBestFriendConsumed extends ConsumedItem {

	public DiosBestFriendConsumed() {
		super("dios_best_friend_consumed", RiskOfRain2Items.DIOS_BEST_FRIEND);
	}

	@Override
	public String getDescription() {
		return "A spent item with no remaining power.";
	}

}
