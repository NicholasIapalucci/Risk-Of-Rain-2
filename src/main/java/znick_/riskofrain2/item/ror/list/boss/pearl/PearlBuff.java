package znick_.riskofrain2.item.ror.list.boss.pearl;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class PearlBuff extends StatBuff {

	public PearlBuff(int itemCount) {
		super(PlayerStat.MAXIMUM_HEALTH_MULTIPLIER, itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public double getStatAdditionAmount(AbstractEntityData entity) {
		return this.getItemCount() * 0.1;
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.PEARL};
	}


}
