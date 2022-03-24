package znick_.riskofrain2.item.ror.list.equipment.elite.nkuhanasretort;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class NkuhanasRetortBuff extends Buff {

	public NkuhanasRetortBuff(int itemCount) {
		super(itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return new ResourceLocation(RiskOfRain2Files.BUFFS + "nkuhanas_retort.png");
	}

	@Override
	public void applyEffect(EntityData entity) {
		
	}

	@Override
	public void removeEffect(EntityData entity) {
		
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.NKUHANAS_RETORT};
	}

}
