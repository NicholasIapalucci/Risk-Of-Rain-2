package znick_.riskofrain2.item.ror.list.white.energydrink;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class EnergyDrinkBuff extends StatBuff {

	public EnergyDrinkBuff(int itemCount) {
		super(PlayerStat.MOVEMENT_SPEED_MULTIPLIER, itemCount, RiskOfRain2Items.ENERGY_DRINK);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public double getStatAdditionAmount(AbstractEntityData entity) {
		return 0.2 * this.getItemCount();
	}
}
