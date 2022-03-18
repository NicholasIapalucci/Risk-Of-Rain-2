package znick_.riskofrain2.item.ror.list.green.rosebuckler;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class RoseBucklerBuff extends StatBuff {

	public RoseBucklerBuff(int itemCount) {
		super(PlayerStat.ARMOR, itemCount, RiskOfRain2Items.ROSE_BUCKLER);
	}

	@Override
	public double getStatAdditionAmount(AbstractEntityData entity) {
		return 30 * this.getItemCount();
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

}
