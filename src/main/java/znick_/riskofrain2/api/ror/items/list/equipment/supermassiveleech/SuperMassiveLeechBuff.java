package znick_.riskofrain2.api.ror.items.list.equipment.supermassiveleech;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class SuperMassiveLeechBuff extends DurationBuff {
	
	private static final ResourceLocation TEXUTRE = new ResourceLocation(RiskOfRain2Files.BUFFS + "super_massive_leech.png");
	
	public SuperMassiveLeechBuff() {
		super((RiskOfRain2Item) RiskOfRain2Items.SUPER_MASSIVE_LEECH, 0, (int) TickHandler.fromSeconds(8));
	}
	
	@Override
	public ResourceLocation getIconTexture() {
		return new ResourceLocation(RiskOfRain2.MODID + ":textures/gui/buffs/super_massive_leech.png");
	}

	@Override
	public void applyEffect(PlayerData player) {
		
	}

	@Override
	public void removeEffect(PlayerData player) {
		
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
