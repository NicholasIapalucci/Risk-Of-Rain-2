package znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.voiditems.weepingfungus;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.VoidItem;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;

public class WeepingFungusItem extends VoidItem implements OnUpdateItem {

	public WeepingFungusItem() {
		super("weeping_fungus");
	}

	@Override
	public String getItemDescription() {
		return "Heal while sprinting.";
	}

	@Override
	public RiskOfRain2Item[] getCorruptedItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.BUSTLING_FUNGUS};
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return true;
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		if (player.isSprinting()) player.addBuff(new WeepingFungusBuff(itemCount));
		else player.removeBuff(WeepingFungusBuff.class);
	}

}
