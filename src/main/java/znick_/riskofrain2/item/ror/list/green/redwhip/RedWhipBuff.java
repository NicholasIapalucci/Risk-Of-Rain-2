package znick_.riskofrain2.item.ror.list.green.redwhip;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class RedWhipBuff extends StatBuff {

	public RedWhipBuff(int itemCount) {
		super(PlayerStat.MOVEMENT_SPEED_MULTIPLIER, itemCount, RiskOfRain2Items.RED_WHIP);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return new ResourceLocation(RiskOfRain2Files.BUFFS + "red_whip.png");
	}

	@Override
	public double getStatAdditionAmount(AbstractEntityData entity) {
		return 0.3 * this.getItemCount();
	}

}
