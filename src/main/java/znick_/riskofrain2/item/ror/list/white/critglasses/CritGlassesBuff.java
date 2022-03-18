package znick_.riskofrain2.item.ror.list.white.critglasses;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class CritGlassesBuff extends StatBuff {

	public CritGlassesBuff(int itemCount) {
		super(PlayerStat.CRIT_CHANCE, itemCount, RiskOfRain2Items.CRIT_GLASSES);
	}

	@Override
	public double getStatAdditionAmount(AbstractEntityData entity) {
		return this.getItemCount() * 0.1; //Can go over 1, will not particularly affect crit. Any value over 1 will just crit every hit.
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

}
