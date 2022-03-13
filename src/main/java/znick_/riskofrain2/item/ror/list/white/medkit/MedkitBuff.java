package znick_.riskofrain2.item.ror.list.white.medkit;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class MedkitBuff extends DurationBuff {

	private static final ResourceLocation TEXTURE = new ResourceLocation(RiskOfRain2Files.BUFFS + "medkit.png");
		
	public MedkitBuff(int itemCount) {
		super(RiskOfRain2Items.MEDKIT, itemCount, (int) TickHandler.fromSeconds(2));
	}
	
	@Override
	public ResourceLocation getIconTexture() {
		return TEXTURE;
	}

	@Override
	public void applyEffect(PlayerData player) {
		
	}
	
	@Override
	public void removeEffect(PlayerData player) {
		if (player.getWorld().isRemote) player.heal(2 + 2 * this.getItemCount());
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
