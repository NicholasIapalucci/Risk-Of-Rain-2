package znick_.riskofrain2.item.ror.list.green.oldwarstealthkit;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class OldWarStealthkitInvisibilityBuff extends DurationBuff {

	public OldWarStealthkitInvisibilityBuff(int itemCount) {
		super(itemCount, TickHandler.fromSeconds(5));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "old_war_stealthkit_invisibility");
	}

	@Override
	public void applyEffect(AbstractEntityData entity) {
		entity.setInvisible();
	}

	@Override
	public void removeEffect(AbstractEntityData entity) {
		entity.setVisible();
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.OLD_WAR_STEALTHKIT};
	}

}
