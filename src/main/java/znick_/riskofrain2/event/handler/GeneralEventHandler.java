package znick_.riskofrain2.event.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.client.gui.RiskOfRain2Gui;
import znick_.riskofrain2.client.gui.menu.RiskOfRain2MainMenu;
import znick_.riskofrain2.entity.elite.EliteEntity;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.list.equipment.elite.EliteEquipmentItem;

public class GeneralEventHandler extends EventHandler {

	@SubscribeEvent
	public void registerEntityData(EntityConstructing event) {
		if (event.entity instanceof EntityLivingBase && AbstractEntityData.get((EntityLivingBase) event.entity) == null) {
			AbstractEntityData.register((EntityLivingBase) event.entity);
			return;
		}
	}
	
	@SubscribeEvent
	public void handleEliteSpawns(EntityJoinWorldEvent event) {
		if (event.entity instanceof EliteEntity) {
			((EliteEntity) event.entity).onEntityCreation();
		}
	}
	
	@SubscribeEvent
	public void handleMobDrops(LivingDropsEvent event) {
		if (event.entityLiving instanceof EliteEntity) {
			if (Math.random() < 1d/4000d) {
				event.drops.add(new EntityItem(
					event.entityLiving.worldObj, 
					event.entityLiving.posX, 
					event.entityLiving.posY, 
					event.entityLiving.posZ, 
					new ItemStack(EliteEquipmentItem.getItemFromEliteType(((EliteEntity) event.entityLiving).getEliteType())))
				);
			}
		}
	}
	
	@SubscribeEvent
	public void renderGui(RenderGameOverlayEvent.Pre event) {
		if (event.type != ElementType.AIR) return;
		new RiskOfRain2Gui();
	}
	
	@SubscribeEvent
	public void saveAndLoadPlayerData(PlayerEvent.Clone event) {
		NBTTagCompound nbt = new NBTTagCompound();
		AbstractEntityData.get(event.original).saveNBTData(nbt);
		AbstractEntityData.get(event.entityPlayer).loadNBTData(nbt);
	}
	
	@SubscribeEvent
	public void onItemPickup(ItemPickupEvent event) {
		// Make sure the item is valid
		if (event.pickedUp == null || event.pickedUp.getEntityItem() == null || event.pickedUp.getEntityItem().getItem() == null) return;
		
		// Initialize some helpful variables
		ItemStack stack = event.pickedUp.getEntityItem();
		Item item = stack.getItem();
		PlayerData player = AbstractEntityData.get(event.player);
		
		// Check if the item is a Risk Of Rain 2 item
		if (item instanceof RiskOfRain2Item) {
			RiskOfRain2Item rorItem = (RiskOfRain2Item) item;
			
			// If the player hasn't found the item, mark it as found.
			if (!player.hasFound(rorItem) && player.hasUnlocked(rorItem)) player.find(rorItem);
		}
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
		AbstractEntityData player = AbstractEntityData.get((EntityPlayer) event.entityLiving);
		
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
		AbstractEntityData player = AbstractEntityData.get((EntityPlayer) event.entityLiving);
		if (player.getBarrier() > 0) player.degenBarrier();
	}
}
