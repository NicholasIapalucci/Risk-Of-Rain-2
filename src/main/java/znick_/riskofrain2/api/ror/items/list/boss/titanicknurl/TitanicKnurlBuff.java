package znick_.riskofrain2.api.ror.items.list.boss.titanicknurl;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.RiskOfRain2Items;

public class TitanicKnurlBuff extends Buff {

	public TitanicKnurlBuff(int itemCount) {
		super((RiskOfRain2Item) RiskOfRain2Items.TITANIC_KNURL, itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.addToMaxHealth(80 * this.getItemCount()); //TODO: Add titanic knurl increased regeneration
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.addToMaxHealth(-80 * this.getItemCount());
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
