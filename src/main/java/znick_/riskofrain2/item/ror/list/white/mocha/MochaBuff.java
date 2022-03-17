package znick_.riskofrain2.item.ror.list.white.mocha;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class MochaBuff extends Buff {

	public MochaBuff(int itemCount) {
		super(itemCount, RiskOfRain2Items.MOCHA);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		player.addToStat(PlayerStat.MOVEMENT_SPEED_MULTIPLIER, 0.07 * this.getItemCount());
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		player.addToStat(PlayerStat.MOVEMENT_SPEED_MULTIPLIER, -0.07 * this.getItemCount());
	}

}
