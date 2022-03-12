package znick_.riskofrain2.item.ror.list.white.armorpiercingrounds;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.buff.StatBuff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class ArmorPiercingRoundsBuff extends StatBuff {

	public ArmorPiercingRoundsBuff(int itemCount) {
		super((RiskOfRain2Item) RiskOfRain2Items.ARMOR_PIERCING_ROUNDS, PlayerStat.DAMAGE_MULTIPLIER, itemCount);
	}

	@Override
	public double getStatAdditionAmount() {
		return this.getItemCount() * 0.2;
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

}
