package znick_.riskofrain2.item.ror.list.white.mocha;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class MochaBuff extends Buff {

	public MochaBuff(int itemCount) {
		super(itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		player.addToStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, 0.07 * this.getItemCount());
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		player.addToStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, -0.07 * this.getItemCount());
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.MOCHA};
	}

}
