package znick_.riskofrain2.item.ror.list.white.oddlyshapedopal;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;

public class OddlyShapedOpalCooldownBuff extends DurationBuff {

	public OddlyShapedOpalCooldownBuff(int itemCount) {
		super(RiskOfRain2Items.ODDLY_SHAPED_OPAL, itemCount, TickHandler.fromSeconds(7));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(PlayerData player) {
		
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.addBuff(new OddlyShapedOpalBuff(this.getItemCount()));
	}

}
