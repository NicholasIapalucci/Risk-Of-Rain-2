package znick_.riskofrain2.item.ror.list.equipment.ocularhud;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class OcularHudBuff extends DurationBuff {

	public OcularHudBuff() {
		super(0, TickHandler.fromSeconds(8));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return new ResourceLocation(RiskOfRain2Files.BUFFS + "ocular_hud.png");
	}

	@Override
	public void applyEffect(EntityData player) {
		player.addToStat(EntityStat.CRIT_CHANCE, 1);
	}

	@Override
	public void removeEffect(EntityData player) {
		player.addToStat(EntityStat.CRIT_CHANCE, -1);
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.OCULAR_HUD};
	}

}
