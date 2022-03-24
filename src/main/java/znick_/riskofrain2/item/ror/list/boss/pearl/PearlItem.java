package znick_.riskofrain2.item.ror.list.boss.pearl;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class PearlItem extends RiskOfRain2Item implements OnUpdateItem {

	public PearlItem() {
		super("pearl");
	}

	@Override
	public void procOnUpdate(LivingUpdateEvent event, EntityData player, int itemCount) {
		player.addBuff(new PearlBuff(itemCount));
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, EntityData player, int itemCount) {
		return true;
	}
	
	@Override
	public ItemCategory getCategory() {
		return ItemCategory.HEALING;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.BOSS;
	}

	@Override
	public String getDescription() {
		return "Increase your maximum health.";
	}

}
