package znick_.riskofrain2.api.ror.artifact;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.ror.artifact.list.ArtifactOfCommand;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.event.rorevents.GenerateItemEvent;

public class ArtifactEventHandler extends EventHandler {

	@SubscribeEvent
	public void artifactOfFrailty(LivingHurtEvent event) {
		if (event.source == DamageSource.fall) event.ammount *= 2;
	}
	
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void artifactOfCommand(GenerateItemEvent event) {
		if (true) return;
		event.setItem(ArtifactOfCommand.getEssenceFromRarity(event.getItem().getRarity()));
	}
}
