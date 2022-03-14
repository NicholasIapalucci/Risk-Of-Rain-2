package znick_.riskofrain2.item.ror.list.red.defensivemicrobots;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class DefensiveMicrobotsCooldownBuff extends DurationBuff {

	public DefensiveMicrobotsCooldownBuff(int itemCount) {
		super(RiskOfRain2Items.DEFENSIVE_MICROBOTS, itemCount, 10);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(PlayerData player) {
		
	}

	@Override
	public void removeEffect(PlayerData player) {
		
	}

}
