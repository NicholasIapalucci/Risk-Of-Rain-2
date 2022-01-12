package znick_.riskofrain2.api.ror.items.boss.pearl;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;

public class PearlBuff extends Buff {

	public PearlBuff(int itemCount) {
		super(itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.addToStat(PlayerStat.MAXIMUM_HEALTH_MULTIPLIER, 0.1);
	}

	@Override
	public void removeEffect(PlayerData player) {
		
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
