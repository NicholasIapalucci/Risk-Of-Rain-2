package znick_.riskofrain2.event.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.items.list.CommandEssence;
import znick_.riskofrain2.client.gui.RiskOfRain2Gui;
import znick_.riskofrain2.client.gui.menu.RiskOfRain2MainMenu;

public class GeneralEventHandler extends EventHandler {

	@SubscribeEvent
	public void registerExtendedPlayer(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && PlayerData.get((EntityPlayer) event.entity) == null) {
			PlayerData.register((EntityPlayer) event.entity);
		}
	}
	
	@SubscribeEvent
	public void renderGui(RenderGameOverlayEvent.Pre event) {
		if (event.type != ElementType.AIR) return;
		new RiskOfRain2Gui();
	}
	
	@SubscribeEvent
	public void useCommandEssence(ItemPickupEvent event) {
		if (event.pickedUp == null || 
			event.pickedUp.getEntityItem() == null || 
			!(event.pickedUp.getEntityItem().getItem() instanceof CommandEssence)) return;
	}
	
	@SubscribeEvent
	public void renderMainMenu(GuiScreenEvent.InitGuiEvent.Post event) {
		if (event.gui instanceof GuiMainMenu && !(event.gui instanceof RiskOfRain2MainMenu)) {
			Minecraft.getMinecraft().displayGuiScreen(new RiskOfRain2MainMenu());
		}
	}
}
