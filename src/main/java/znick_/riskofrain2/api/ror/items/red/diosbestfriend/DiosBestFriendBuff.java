package znick_.riskofrain2.api.ror.items.red.diosbestfriend;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.Tick;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class DiosBestFriendBuff extends DurationBuff {

	private static final ResourceLocation TEXTURE = new ResourceLocation(RiskOfRain2Files.BUFFS + "dios_best_friend.png");
	
	public DiosBestFriendBuff(int itemCount) {
		super(itemCount, (int) Tick.fromSeconds(3));
	}
	
	@Override
	public ResourceLocation getIconTexture() {
		return TEXTURE;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.getPlayer().capabilities.disableDamage = true;
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.getPlayer().capabilities.disableDamage = false;
	}

	@Override
	public boolean isDebuff() {
		return false;
	}
}
