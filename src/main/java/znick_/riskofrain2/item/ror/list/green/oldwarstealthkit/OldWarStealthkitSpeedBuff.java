package znick_.riskofrain2.item.ror.list.green.oldwarstealthkit;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class OldWarStealthkitSpeedBuff extends DurationBuff {

	public OldWarStealthkitSpeedBuff(int itemCount) {
		super(itemCount, TickHandler.fromSeconds(5));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "old_war_stealthkit_speed");
	}

	@Override
	public void applyEffect(AbstractEntityData entity) {
		entity.addToStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, 0.4);
	}

	@Override
	public void removeEffect(AbstractEntityData entity) {
		entity.removeFromStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, 0.4);
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.OLD_WAR_STEALTHKIT};
	}
}
