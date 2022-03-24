package znick_.riskofrain2.item.ror.list.white.personalshieldgenerator;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class PersonalShieldBuff extends StatBuff {

	public PersonalShieldBuff(int itemCount) {
		super(EntityStat.MAX_SHIELD, itemCount);
	}

	@Override
	public double getStatAdditionAmount(EntityData entity) {
		return entity.getMaxHealth() * 0.08 * this.getItemCount();
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.PERSONAL_SHIELD};
	}

}
