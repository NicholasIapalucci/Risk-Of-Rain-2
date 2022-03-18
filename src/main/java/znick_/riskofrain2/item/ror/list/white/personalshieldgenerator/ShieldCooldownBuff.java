package znick_.riskofrain2.item.ror.list.white.personalshieldgenerator;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;

public class ShieldCooldownBuff extends DurationBuff {

	public ShieldCooldownBuff(int itemCount) {
		super(itemCount, TickHandler.fromSeconds(7), RiskOfRain2Items.PERSONAL_SHIELD);
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
