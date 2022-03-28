package znick_.riskofrain2.item.ror.list.equipment.elite.nkuhanasretort;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class HealingDisabledBuff extends DurationBuff {

	public HealingDisabledBuff(int itemCount) {
		super(itemCount, TickHandler.fromSeconds(8));
	}
	
	@Override
	public boolean isDebuff() {
		return true;
	}

	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "healing_disabled.png");
	}

	@Override
	public void applyEffect(AbstractEntityData entity) {
		entity.disableHealing();
	}

	@Override
	public void removeEffect(AbstractEntityData entity) {
		entity.enableHealing();
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {};
	}

}
