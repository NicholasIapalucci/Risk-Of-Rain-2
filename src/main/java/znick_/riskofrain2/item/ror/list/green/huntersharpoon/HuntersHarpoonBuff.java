package znick_.riskofrain2.item.ror.list.green.huntersharpoon;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.StackableBuff;
import znick_.riskofrain2.api.ror.buff.stat.EntityStat;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class HuntersHarpoonBuff extends DurationBuff implements StackableBuff {
	
	public HuntersHarpoonBuff(int itemCount) {
		super(itemCount, 0);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "hunters_harpoon.png");
	}

	@Override
	public void applyEffect(AbstractEntityData entity) {
		entity.addToStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, 0.25);
	}

	@Override
	public void removeEffect(AbstractEntityData entity) {
		entity.removeFromStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, 0.25);
	}

	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.HUNTERS_HARPOON};
	}

}
