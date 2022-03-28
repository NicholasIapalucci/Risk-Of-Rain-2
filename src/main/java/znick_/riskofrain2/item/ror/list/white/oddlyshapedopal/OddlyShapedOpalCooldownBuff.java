package znick_.riskofrain2.item.ror.list.white.oddlyshapedopal;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class OddlyShapedOpalCooldownBuff extends DurationBuff {

	public OddlyShapedOpalCooldownBuff(int itemCount) {
		super(itemCount, TickHandler.fromSeconds(7));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		player.addBuff(new OddlyShapedOpalBuff(this.getItemCount()));
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.ODDLY_SHAPED_OPAL};
	}

}
