package znick_.riskofrain2.item.ror.list.white.cautiousslug;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class CautiousSlugBuff extends Buff {

	public CautiousSlugBuff(int itemCount) {
		super(RiskOfRain2Items.CAUTIOUS_SLUG, itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.playSound("ror2:cautious_slug_start");
		player.addToStat(PlayerStat.REGEN_SPEED_MULTIPLIER, 2);
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.playSound("ror2:cautious_slug_stop");
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
