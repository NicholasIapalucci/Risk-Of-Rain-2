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
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.client.gui.RiskOfRain2Gui;
import znick_.riskofrain2.client.gui.menu.RiskOfRain2MainMenu;
import znick_.riskofrain2.item.ror.list.CommandEssence;

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
	
	@SubscribeEvent
	public void handleBarrier(LivingHurtEvent event) {
		if (!(event.entityLiving instanceof EntityPlayer)) return;
		PlayerData player = PlayerData.get((EntityPlayer) event.entityLiving);
		
		// Check if the player's barrier is enough to soak up all the damage
		if (player.getBarrier() >= event.ammount) {
			// If so, make it so the player doesn't get damaged
			event.setCanceled(true);
			// And take away the damage from the barrier
			player.removeBarrier((int) event.ammount);
		}
		
		// Otherwise, handle the case where the barrier can't soak up all the damage
		else {
			// Deduct the barrier from the damage amount
			event.ammount -= player.getBarrier();
			// Remove the barrier
			player.setBarrier(0);
		}
	}
	
	@SubscribeEvent
	public void handleBarrierDegen(LivingUpdateEvent event) {
		if (!(event.entityLiving instanceof EntityPlayer)) return;
		PlayerData player = PlayerData.get((EntityPlayer) event.entityLiving);
		if (player.getBarrier() > 0) player.tickBarrier();
	}
}
