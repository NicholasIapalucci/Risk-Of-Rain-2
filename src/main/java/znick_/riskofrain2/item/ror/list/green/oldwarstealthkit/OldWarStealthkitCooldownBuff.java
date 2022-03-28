package znick_.riskofrain2.item.ror.list.green.oldwarstealthkit;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class OldWarStealthkitCooldownBuff extends DurationBuff {

	public OldWarStealthkitCooldownBuff(int itemCount) {
		super(itemCount, TickHandler.fromSeconds(60 * Math.pow(0.5, itemCount)));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(AbstractEntityData entity) {
		
	}

	@Override
	public void removeEffect(AbstractEntityData entity) {
		
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.OLD_WAR_STEALTHKIT};
	}

}
