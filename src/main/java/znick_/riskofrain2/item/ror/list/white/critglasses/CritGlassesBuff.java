package znick_.riskofrain2.item.ror.list.white.critglasses;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class CritGlassesBuff extends StatBuff {

	public CritGlassesBuff(int itemCount) {
		super((RiskOfRain2Item) RiskOfRain2Items.CRIT_GLASSES, PlayerStat.CRIT_CHANCE, itemCount);
	}

	@Override
	public double getStatAdditionAmount() {
		return this.getItemCount() * 0.1; //Can go over 1, will not particularly affect crit. Any value over 1 will just crit every hit.
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

}
