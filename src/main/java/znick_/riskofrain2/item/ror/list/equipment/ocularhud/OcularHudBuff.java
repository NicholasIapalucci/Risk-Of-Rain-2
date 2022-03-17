package znick_.riskofrain2.item.ror.list.equipment.ocularhud;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class OcularHudBuff extends DurationBuff {

	public OcularHudBuff() {
		super(RiskOfRain2Items.OCULAR_HUD, 0, TickHandler.fromSeconds(8));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		player.addToStat(PlayerStat.CRIT_CHANCE, 1);
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		player.addToStat(PlayerStat.CRIT_CHANCE, -1);
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
