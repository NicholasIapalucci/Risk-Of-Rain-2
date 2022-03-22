package znick_.riskofrain2.item.ror.list.red.fiftysevenleafclover;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class FiftySevenLeafCloverBuff extends StatBuff {

	public FiftySevenLeafCloverBuff(int itemCount) {
		super(PlayerStat.LUCK, itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.FIFTY_SEVEN_LEAF_CLOVER};
	}

	@Override
	public double getStatAdditionAmount(AbstractEntityData entity) {
		return this.getItemCount();
	}

}
