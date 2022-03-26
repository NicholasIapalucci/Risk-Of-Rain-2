package znick_.riskofrain2.item.ror.list.equipment.supermassiveleech;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class SuperMassiveLeechBuff extends DurationBuff {
		
	public SuperMassiveLeechBuff() {
		super(0, TickHandler.fromSeconds(8));
	}
	
	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "super_massive_leech.png");
	}

	@Override
	public void applyEffect(EntityData player) {
		
	}

	@Override
	public void removeEffect(EntityData player) {
		
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.SUPER_MASSIVE_LEECH};
	}

}
