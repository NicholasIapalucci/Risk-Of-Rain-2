package znick_.riskofrain2.item.ror.list.white.personalshieldgenerator;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;

public class PersonalShieldBuff extends StatBuff {

	public PersonalShieldBuff(int itemCount) {
		super(PlayerStat.MAX_SHIELD, itemCount, RiskOfRain2Items.PERSONAL_SHIELD);
	}

	@Override
	public double getStatAdditionAmount(AbstractEntityData entity) {
		return entity.getMaxHealth() * 0.08 * this.getItemCount();
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

}
