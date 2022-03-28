package znick_.riskofrain2.item.ror.list.green.warhorn;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class WarHornBuff extends DurationBuff {

	public WarHornBuff(int itemCount) {
		super(itemCount, TickHandler.fromSeconds(4 + 4 * itemCount));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(AbstractEntityData entity) {
		entity.addToStat(EntityStat.ATTACK_SPEED_MULTIPLIER, 0.7);
	}

	@Override
	public void removeEffect(AbstractEntityData entity) {
		entity.removeFromStat(EntityStat.ATTACK_SPEED_MULTIPLIER, 0.7);
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.WAR_HORN};
	}

}
