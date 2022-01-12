package znick_.riskofrain2.api.ror.items.green.infusion;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;

public class InfusionBuff extends Buff {

	public InfusionBuff(int itemCount) {
		super(itemCount);
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
