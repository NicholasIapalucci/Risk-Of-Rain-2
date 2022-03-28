package znick_.riskofrain2.item.ror.list.red.diosbestfriend;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class DiosBestFriendBuff extends DurationBuff {
	
	public DiosBestFriendBuff(int itemCount) {
		super(itemCount, (int) TickHandler.fromSeconds(3));
	}
	
	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "dios_best_friend.png");
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		player.setInvulnerable();
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		player.setVulnerable();
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.DIOS_BEST_FRIEND};
	}
}
