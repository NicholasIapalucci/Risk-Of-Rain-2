package znick_.riskofrain2.item.ror.list.white.energydrink;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class EnergyDrinkBuff extends StatBuff {

	public EnergyDrinkBuff(int itemCount) {
		super((RiskOfRain2Item) RiskOfRain2Items.ENERGY_DRINK, PlayerStat.MOVEMENT_SPEED_MULTIPLIER, itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public double getStatAdditionAmount() {
		return 0.2 * this.getItemCount();
	}
}
