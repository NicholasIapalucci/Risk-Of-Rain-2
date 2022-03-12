package znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.voiditems.saferspaces;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class SaferSpacesBuff extends Buff {

	public SaferSpacesBuff(int itemCount) {
		super((RiskOfRain2Item) RiskOfRain2Items.SAFER_SPACES, itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return new ResourceLocation(RiskOfRain2Files.BUFFS + "safer_spaces.png");
	}

	@Override
	public void applyEffect(PlayerData player) {
		
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.addBuff(new SaferSpacesCooldownBuff(this.getItemCount()));
	}

}
