package znick_.riskofrain2.item.ror.list.equipment.supermassiveleech;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class SuperMassiveLeechBuff extends DurationBuff {
	
	private static final ResourceLocation TEXUTRE = new ResourceLocation(RiskOfRain2Files.BUFFS + "super_massive_leech.png");
	
	public SuperMassiveLeechBuff() {
		super(0, (int) TickHandler.fromSeconds(8), RiskOfRain2Items.SUPER_MASSIVE_LEECH);
	}
	
	@Override
	public ResourceLocation getIconTexture() {
		return new ResourceLocation(RiskOfRain2Mod.MODID + ":textures/gui/buffs/super_massive_leech.png");
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
