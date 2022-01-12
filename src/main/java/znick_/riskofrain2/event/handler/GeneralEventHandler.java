package znick_.riskofrain2.event.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.character.PlayableCharacter;
import znick_.riskofrain2.client.gui.GuiOverlay;
import znick_.riskofrain2.client.gui.RiskOfRain2Gui;
import znick_.riskofrain2.event.handler.character.HuntressEventHandler;

public class GeneralEventHandler extends EventHandler {

	@SubscribeEvent
	public void registerExtendedPlayer(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && PlayerData.get((EntityPlayer) event.entity) == null) {
			PlayerData.register((EntityPlayer) event.entity);
		}
	}
	
	@SubscribeEvent
	public void renderGui(RenderGameOverlayEvent.Post event) {
		if (event.type != ElementType.EXPERIENCE) return;
		new RiskOfRain2Gui();
	}
}
