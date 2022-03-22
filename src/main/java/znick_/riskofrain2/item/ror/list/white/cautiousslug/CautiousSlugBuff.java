package znick_.riskofrain2.item.ror.list.white.cautiousslug;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class CautiousSlugBuff extends Buff {

	public CautiousSlugBuff(int itemCount) {
		super(itemCount);
	}
	
	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		player.heal(0.05f * this.getItemCount());
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		
	}
	
	@Override
	public boolean shouldRepeat() {
		return true;
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.CAUTIOUS_SLUG};
	}


}
