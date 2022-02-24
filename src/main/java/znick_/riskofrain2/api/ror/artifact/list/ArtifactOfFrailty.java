package znick_.riskofrain2.api.ror.artifact.list;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.ror.artifact.Artifact;

public class ArtifactOfFrailty extends Artifact {

	public ArtifactOfFrailty() {
		super("frailty");
	}

	@SubscribeEvent
	public void proc(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer && event.source == DamageSource.fall) {
			event.ammount *= 2;
		}
	}
	
	@Override
	public String getDescription() {
		return "Fall damage is doubled and lethal.";
	}

	@Override
	public Shape[] getTabletConfiguration() {
		return new Shape[] {
			CIRCLE,   CIRCLE,   CIRCLE,
			TRIANGLE, CIRCLE,   TRIANGLE,
			TRIANGLE, TRIANGLE, TRIANGLE
		};
	}
	
	

}
