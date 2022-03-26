package znick_.riskofrain2.item.ror.list.equipment.jadeelephant;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class JadeElephantBuff extends DurationBuff {
	
	public JadeElephantBuff() {
		super(0, TickHandler.fromSeconds(5));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "jade_elephant");
	}

	@Override
	public void applyEffect(EntityData player) {
		player.addToStat(EntityStat.ARMOR, 500);
	}

	@Override
	public void removeEffect(EntityData player) {
		player.addToStat(EntityStat.ARMOR, -500);
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.JADE_ELEPHANT};
	}

}
