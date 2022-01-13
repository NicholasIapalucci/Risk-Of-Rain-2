package znick_.riskofrain2.api.ror.items.red.fiftysevenleafclover;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.RiskOfRain2Items;

public class FiftySevenLeafCloverBuff extends Buff {

	public FiftySevenLeafCloverBuff(int itemCount) {
		super((RiskOfRain2Item) RiskOfRain2Items.FIFTY_SEVEN_LEAF_CLOVER, itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.addToStat(PlayerStat.LUCK, this.getItemCount());
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.addToStat(PlayerStat.LUCK, -this.getItemCount());
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
