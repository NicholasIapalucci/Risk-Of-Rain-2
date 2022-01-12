package znick_.riskofrain2.api.ror.items.equipment.ocularhud;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.event.Tick;

public class OcularHudBuff extends DurationBuff {

	public OcularHudBuff() {
		super(0, Tick.fromSeconds(8));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.addToStat(PlayerStat.CRIT_CHANCE, 1);
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.addToStat(PlayerStat.CRIT_CHANCE, -1);
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
