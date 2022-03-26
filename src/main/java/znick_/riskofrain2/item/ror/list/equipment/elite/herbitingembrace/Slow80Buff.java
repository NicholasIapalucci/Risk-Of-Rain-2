package znick_.riskofrain2.item.ror.list.equipment.elite.herbitingembrace;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class Slow80Buff extends DurationBuff {

	public Slow80Buff(int itemCount) {
		super(itemCount, TickHandler.fromSeconds(1.5));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "slow_80.png");
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.HER_BITING_EMBRACE};
	}

	@Override
	public void applyEffect(EntityData entity) {
		entity.removeFromStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, 0.8);
	}

	@Override
	public void removeEffect(EntityData entity) {
		entity.addToStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, 0.8);
	}

}
