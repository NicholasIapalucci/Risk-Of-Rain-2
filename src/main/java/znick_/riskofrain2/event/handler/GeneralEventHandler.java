package znick_.riskofrain2.event.handler;

import java.lang.reflect.Field;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import znick_.riskofrain2.api.mc.CustomRegenHandler;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.client.gui.RiskOfRain2Gui;

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
