package znick_.riskofrain2.item.ror.list.boss.titanicknurl;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class TitanicKnurlBuff extends Buff {

	public TitanicKnurlBuff(int itemCount) {
		super(itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		player.addToMaxHealth(80 * this.getItemCount()); //TODO: Add titanic knurl increased regeneration
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		player.addToMaxHealth(-80 * this.getItemCount());
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.TITANIC_KNURL};
	}

}
