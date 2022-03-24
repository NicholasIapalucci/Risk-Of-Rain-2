package znick_.riskofrain2.item.ror.list.white.stungrenade;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class StunGrenadeBuff extends DurationBuff {

	public StunGrenadeBuff(int itemCount) {
		super(itemCount, TickHandler.fromSeconds(2));
	}

	@Override
	public void applyEffect(EntityData entity) {
		entity.getEntity().motionX = 0;
		entity.getEntity().motionY = 0;
		entity.getEntity().motionZ = 0;
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void removeEffect(EntityData player) {
		
	}

	@Override
	public boolean shouldRepeat() {
		return true;
	}
	
	@Override
	public boolean isDebuff() {
		return true;
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.STUN_GRENADE};
	}
}
