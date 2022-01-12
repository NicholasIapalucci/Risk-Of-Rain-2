package znick_.riskofrain2.api.ror.items.equipment.jadeelephant;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.event.Tick;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class JadeElephantBuff extends DurationBuff {

	private static final ResourceLocation TEXTURE = new ResourceLocation(RiskOfRain2Files.BUFFS + "jade_elephant");
	
	public JadeElephantBuff() {
		super(0, Tick.fromSeconds(5));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return TEXTURE;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.addToStat(PlayerStat.ARMOR, 500);
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.addToStat(PlayerStat.ARMOR, -500);
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
