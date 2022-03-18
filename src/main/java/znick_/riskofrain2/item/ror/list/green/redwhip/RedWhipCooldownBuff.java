package znick_.riskofrain2.item.ror.list.green.redwhip;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class RedWhipCooldownBuff extends DurationBuff {

	public RedWhipCooldownBuff(int itemCount) {
		super(itemCount, TickHandler.fromSeconds(5), RiskOfRain2Items.RED_WHIP);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(AbstractEntityData entity) {
		
	}

	@Override
	public void removeEffect(AbstractEntityData entity) {

	}

}
