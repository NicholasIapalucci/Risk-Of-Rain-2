package znick_.riskofrain2.api.ror.artifact;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.event.handler.EventHandler;

public class ArtifactEventHandler extends EventHandler {

	@SubscribeEvent
	public void artifactOfFrailty(LivingHurtEvent event) {
		if (event.source == DamageSource.fall) event.ammount *= 2;
	}
}
