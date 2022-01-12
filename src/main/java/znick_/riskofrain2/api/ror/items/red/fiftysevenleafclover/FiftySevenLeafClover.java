package znick_.riskofrain2.api.ror.items.red.fiftysevenleafclover;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.item.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class FiftySevenLeafClover extends RiskOfRain2Item implements OnUpdateItem {

	public FiftySevenLeafClover() {
		super("fifty_seven_leaf_clover");
	}

	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		player.addBuff(new FiftySevenLeafCloverBuff(itemCount));
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return true;
	}
	
	@Override
	public String getProperName() {
		return "57 Leaf Clover";
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.RED;
	}

	@Override
	public String getDescription() {
		return "Luck is on your side.";
	}
}
