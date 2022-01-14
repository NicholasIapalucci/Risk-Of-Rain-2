package znick_.riskofrain2.api.ror.items.list.white.stungrenade;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.event.Tick;
import znick_.riskofrain2.item.RiskOfRain2Items;

public class StunGrenadeBuff extends DurationBuff {

	public StunGrenadeBuff(int itemCount) {
		super((RiskOfRain2Item) RiskOfRain2Items.STUN_GRENADE, itemCount, Tick.fromSeconds(2));
	}

	@Override
	public void applyEffect(PlayerData player) { //TODO: Update buffs to be applicable to any entity
		player.getPlayer().motionX = 0;
		player.getPlayer().motionY = 0;
		player.getPlayer().motionZ = 0;
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void removeEffect(PlayerData player) {
		
	}

	@Override
	public boolean isDebuff() {
		return true;
	}
}
