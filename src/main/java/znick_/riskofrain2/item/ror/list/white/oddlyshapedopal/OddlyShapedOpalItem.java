package znick_.riskofrain2.item.ror.list.white.oddlyshapedopal;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.dlc.DLC;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class OddlyShapedOpalItem extends RiskOfRain2Item implements OnHurtItem, OnUpdateItem {

	public OddlyShapedOpalItem() {
		super("oddly_shaped_opal");
	}

	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		player.addBuff(new OddlyShapedOpalBuff(itemCount));
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return !(player.hasBuff(OddlyShapedOpalBuff.class) || player.hasBuff(OddlyShapedOpalCooldownBuff.class));
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		return player.hasBuff(OddlyShapedOpalBuff.class);
	}
	
	@Override
	public void procOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		player.removeBuff(OddlyShapedOpalBuff.class);
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Reduce damage the first time you are hit.";
	}
	
	@Override
	public DLC getDLC() {
		return DLC.SURVIVORS_OF_THE_VOID;
	}

}
