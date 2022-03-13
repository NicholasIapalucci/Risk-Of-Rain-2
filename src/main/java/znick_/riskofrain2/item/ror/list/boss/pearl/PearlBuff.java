package znick_.riskofrain2.item.ror.list.boss.pearl;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class PearlBuff extends Buff {

	public PearlBuff(int itemCount) {
		super(RiskOfRain2Items.PEARL, itemCount);
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
