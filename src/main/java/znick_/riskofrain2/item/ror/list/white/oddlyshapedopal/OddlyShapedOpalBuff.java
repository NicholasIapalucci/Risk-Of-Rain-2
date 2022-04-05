package znick_.riskofrain2.item.ror.list.white.oddlyshapedopal;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.stat.EntityStat;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class OddlyShapedOpalBuff extends Buff {

	public OddlyShapedOpalBuff(int itemCount) {
		super(itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "oddly_shaped_opal.png");
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		player.addToStat(EntityStat.ARMOR, 100 * this.getItemCount());
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		player.addToStat(EntityStat.ARMOR, -100 * this.getItemCount());
		player.addBuff(new OddlyShapedOpalCooldownBuff(this.getItemCount()));
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.ODDLY_SHAPED_OPAL};
	}

}
