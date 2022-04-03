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
	
	@SubscribeEvent
	public void artifactOfCommand(GenerateItemEvent event) {
		if (!PlayerData.get(event.entityPlayer).hasArtifactEnabled(Artifact.COMMAND)) return;
		event.setCanceled(true);
		if (!event.entityPlayer.worldObj.isRemote) return;
		TileEntity tile = event.getSource();
		tile.getWorldObj().spawnEntityInWorld(new CommandEssenceEntity(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord + 1, event.getItem().getRarity()));
	}
}
