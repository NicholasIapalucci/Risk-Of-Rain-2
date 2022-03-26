package znick_.riskofrain2.event.handler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
import cpw.mods.fml.common.network.internal.FMLMessage;
import cpw.mods.fml.relauncher.Side;
import io.netty.channel.embedded.EmbeddedChannel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.client.gui.RiskOfRain2Gui;
import znick_.riskofrain2.client.gui.commandessence.CommandEssenceGui;
import znick_.riskofrain2.client.gui.menu.RiskOfRain2MainMenu;
import znick_.riskofrain2.entity.elite.EliteEntity;
import znick_.riskofrain2.entity.inanimate.CommandEssenceEntity;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.list.equipment.elite.EliteEquipmentItem;
import znick_.riskofrain2.item.ror.list.white.personalshieldgenerator.ShieldCooldownBuff;

public class GeneralEventHandler extends EventHandler {

	@SubscribeEvent
	public void registerEntityData(EntityConstructing event) {
		if (event.entity instanceof EntityLivingBase && EntityData.get((EntityLivingBase) event.entity) == null) {
			EntityData.register((EntityLivingBase) event.entity);
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
		EntityData.get(event.original).saveNBTData(nbt);
		EntityData.get(event.entityPlayer).loadNBTData(nbt);
	}
	
	@SubscribeEvent
	public void onItemPickup(ItemPickupEvent event) {
		// Make sure the item is valid
		if (event.pickedUp == null || event.pickedUp.getEntityItem() == null || event.pickedUp.getEntityItem().getItem() == null) return;
		
		// Initialize some helpful variables
		ItemStack stack = event.pickedUp.getEntityItem();
		Item item = stack.getItem();
		PlayerData player = EntityData.get(event.player);
		
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
	public void handleBarrierAndShield(LivingHurtEvent event) {
		EntityData entity = EntityData.get(event.entityLiving);
		entity.addBuff(new ShieldCooldownBuff(0));
		
		// Check if the player's barrier is enough to soak up all the damage
		if (entity.getStat(EntityStat.BARRIER) >= event.ammount) {
			// If so, make it so the player doesn't get damaged
			event.setCanceled(true);
			// And take away the damage from the barrier
			entity.removeFromStat(EntityStat.BARRIER, event.ammount);
		}
		
		// Otherwise, handle the case where the barrier can't soak up all the damage
		else {
			// Deduct the barrier from the damage amount
			event.ammount -= entity.getStat(EntityStat.BARRIER);
			// Remove the barrier
			entity.setStat(EntityStat.BARRIER, 0);
		}
		
		// Check if the player's shield is enough to soak up all the damage
		if (entity.getStat(EntityStat.SHIELD) >= event.ammount) {
			event.setCanceled(true);
			entity.removeFromStat(EntityStat.SHIELD, event.ammount);
		}
		
		// Otherwise, handle the case where the shield can't soak up all the damage
		else {
			event.ammount -= entity.getStat(EntityStat.SHIELD);
			entity.setStat(EntityStat.SHIELD, 0);
		}
	}
	
	@SubscribeEvent
	public void handleBarrierAndShield(LivingUpdateEvent event) {
		EntityData entity = EntityData.get(event.entityLiving);
		if (entity.getStat(EntityStat.BARRIER) > 0) entity.removeFromStat(EntityStat.BARRIER, 0.1);
		if (entity.getStat(EntityStat.SHIELD) < entity.getStat(EntityStat.MAX_SHIELD) && !entity.hasBuff(ShieldCooldownBuff.class)) {
			entity.addToStat(EntityStat.SHIELD, 0.1);
			if (entity.getStat(EntityStat.SHIELD) > entity.getStat(EntityStat.MAX_SHIELD));
			entity.setStat(EntityStat.SHIELD, entity.getStat(EntityStat.MAX_SHIELD));
		}
	}
	
	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event) {
		if (event.target instanceof CommandEssenceEntity && event.entityPlayer.worldObj.isRemote) {
			ModContainer mc = FMLCommonHandler.instance().findContainerFor(RiskOfRain2Mod.instance);
			Object guiContainer = NetworkRegistry.INSTANCE.getLocalGuiContainer(
				mc, 
				Minecraft.getMinecraft().thePlayer, 
				CommandEssenceGui.GUI_ID, 
				Minecraft.getMinecraft().theWorld, 
				(int) event.target.posX,
				(int) event.target.posY, 
				(int) event.target.posZ
			);
			CommandEssenceGui gui = (CommandEssenceGui) guiContainer;
			gui.setCommandEssenceEntity((CommandEssenceEntity) event.target);
            FMLCommonHandler.instance().showGuiScreen(guiContainer);
		}
	}
}
