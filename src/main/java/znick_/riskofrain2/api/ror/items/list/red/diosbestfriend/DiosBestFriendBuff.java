package znick_.riskofrain2.api.ror.items.list.red.diosbestfriend;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class DiosBestFriendBuff extends DurationBuff {

	private static final ResourceLocation TEXTURE = new ResourceLocation(RiskOfRain2Files.BUFFS + "dios_best_friend.png");
	
	public DiosBestFriendBuff(int itemCount) {
		super((RiskOfRain2Item) RiskOfRain2Items.DIOS_BEST_FRIEND, itemCount, (int) TickHandler.fromSeconds(3));
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
