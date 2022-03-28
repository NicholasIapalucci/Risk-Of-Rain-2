package znick_.riskofrain2.item.ror.list.red.fiftysevenleafclover;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class FiftySevenLeafClover extends RiskOfRain2Item implements OnUpdateItem {

	public FiftySevenLeafClover() {
		super("fifty_seven_leaf_clover");
	}

	@Override
	public void procOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount) {
		player.addBuff(new FiftySevenLeafCloverBuff(itemCount));
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount) {
		return true;
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
