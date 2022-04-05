package znick_.riskofrain2.api.ror.artifact;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.entity.inanimate.CommandEssenceEntity;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.event.rorevents.GenerateItemEvent;

public class ArtifactEventHandler extends EventHandler {

	@SubscribeEvent
	public void artifactOfFrailty(LivingHurtEvent event) {
		if (event.source == DamageSource.fall) event.ammount *= 2;
	}
	
	/**
	 * Procs the artifact of command. Listens for a {@link GenerateItemEvent} and if the player has
	 * the artifact of command enabled, it will cancel the event and spawn a command essence entity
	 * instead.
	 * 
	 * @param event The {@code GenerateItemEvent}.
	 */
	@SubscribeEvent
	public void artifactOfCommand(GenerateItemEvent event) {
		if (!PlayerData.get(event.entityPlayer).hasArtifactEnabled(Artifact.COMMAND)) return;
		event.setCanceled(true);
		
		// If running on the client, spawn a command essence entity
		if (event.entityPlayer.worldObj.isRemote) {
			TileEntity tile = event.getSource();
			tile.getWorldObj().spawnEntityInWorld(new CommandEssenceEntity(
				tile.getWorldObj(), 
				tile.xCoord, 
				tile.yCoord, 
				tile.zCoord + 1, 
				event.getItem().getRarity()
			));
		}
	}
}
