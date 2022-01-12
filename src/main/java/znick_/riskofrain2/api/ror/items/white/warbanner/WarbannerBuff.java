package znick_.riskofrain2.api.ror.items.white.warbanner;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.event.Tick;

/**
 * The buff the player receives when in radius of a warbanner. The buff normally would not be a {@code DurationBuff},
 * but the warbanner tile entity has no efficient way of removing the buff when a player leaves the radius, so the 
 * buff must expire naturally by being a {@code DurationBuff}.
 * 
 * @author Nicholas Iapalucci
 */
public class WarbannerBuff extends DurationBuff {
	
	public WarbannerBuff(int itemCount) {
		super(itemCount, (int) Tick.fromSeconds(3));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return new ResourceLocation(RiskOfRain2.MODID + ":textures/gui/buffs/warbanner.png");
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.addToStat(PlayerStat.MOVEMENT_SPEED_MULTIPLIER, 0.3);
	}
	
	@Override
	public void removeEffect(PlayerData player) {
		player.addToStat(PlayerStat.MOVEMENT_SPEED_MULTIPLIER, -0.3);
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
