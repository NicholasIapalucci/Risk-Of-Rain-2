package znick_.riskofrain2.item.ror.list.green.rosebuckler;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class RoseBucklerBuff extends StatBuff {

	public RoseBucklerBuff(int itemCount) {
		super(EntityStat.ARMOR, itemCount);
	}

	@Override
	public double getStatAdditionAmount(AbstractEntityData entity) {
		return 30 * this.getItemCount();
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.ROSE_BUCKLER};
	}

}
