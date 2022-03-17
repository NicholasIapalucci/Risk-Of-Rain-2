package znick_.riskofrain2.item.ror.list.white.stungrenade;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class StunGrenadeBuff extends DurationBuff {

	public StunGrenadeBuff(int itemCount) {
		super(RiskOfRain2Items.STUN_GRENADE, itemCount, TickHandler.fromSeconds(2));
	}

	@Override
	public void applyEffect(AbstractEntityData player) { //TODO: Update buffs to be applicable to any entity
		player.getEntity().motionX = 0;
		player.getEntity().motionY = 0;
		player.getEntity().motionZ = 0;
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		
	}

	@Override
	public boolean isDebuff() {
		return true;
	}
}
