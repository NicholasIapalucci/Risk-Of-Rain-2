package znick_.riskofrain2.api.ror.items.list.green.infusion;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.RiskOfRain2Items;

public class InfusionBuff extends Buff {

	public InfusionBuff(int itemCount) {
		super((RiskOfRain2Item) RiskOfRain2Items.INFUSION, itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return null;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.addToMaxHealth(this.getItemCount() * 4);
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.addToMaxHealth(-this.getItemCount() * 4);
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
