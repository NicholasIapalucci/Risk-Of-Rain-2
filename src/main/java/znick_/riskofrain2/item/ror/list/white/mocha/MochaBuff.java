package znick_.riskofrain2.item.ror.list.white.mocha;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class MochaBuff extends Buff {

	public MochaBuff(int itemCount) {
		super(RiskOfRain2Items.MOCHA, itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.addToStat(PlayerStat.MOVEMENT_SPEED_MULTIPLIER, 0.07 * this.getItemCount());
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.addToStat(PlayerStat.MOVEMENT_SPEED_MULTIPLIER, -0.07 * this.getItemCount());
	}

}
