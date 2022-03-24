package znick_.riskofrain2.item.ror.list.equipment.elite.nkuhanasretort;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

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
		return new ResourceLocation(RiskOfRain2Files.BUFFS + "healing_disabled.png");
	}

	@Override
	public void applyEffect(EntityData entity) {
		entity.disableHealing();
	}

	@Override
	public void removeEffect(EntityData entity) {
		entity.enableHealing();
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {};
	}

}
