package znick_.riskofrain2.item.ror.list.red.defensivemicrobots;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class DefensiveMicrobotsCooldownBuff extends DurationBuff {

	public DefensiveMicrobotsCooldownBuff(int itemCount) {
		super(itemCount, (int) (10d/(itemCount)));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(EntityData player) {
		
	}

	@Override
	public void removeEffect(EntityData player) {
		
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.DEFENSIVE_MICROBOTS};
	}

}
