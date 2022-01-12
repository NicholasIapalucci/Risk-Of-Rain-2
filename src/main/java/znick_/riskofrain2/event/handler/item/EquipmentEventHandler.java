package znick_.riskofrain2.event.handler.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import znick_.riskofrain2.api.ror.items.equipment.RiskOfRain2Equipment;
import znick_.riskofrain2.api.ror.items.equipment.supermassiveleech.SuperMassiveLeechItem;
import znick_.riskofrain2.client.keybind.RiskOfRain2KeyBinds;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.helper.InventoryHelper;

public class EquipmentEventHandler extends EventHandler {

	@SubscribeEvent //TODO: Update this to be more secure. Check for items popping into inv through chests and whatnot.
	public void replaceEquipmentOnDrop(EntityItemPickupEvent event){
		EntityPlayer player = event.entityPlayer;
		if (event.item.getEntityItem().getItem() instanceof RiskOfRain2Equipment) {
			int equipmentSlot = InventoryHelper.getEquipmentSlot(player);
			if (equipmentSlot != -1) {
				RiskOfRain2Equipment prevEquip = (RiskOfRain2Equipment) player.inventory.getStackInSlot(equipmentSlot).getItem();
				player.inventory.setInventorySlotContents(equipmentSlot, null);
				InventoryHelper.dropItemInWorld(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(prevEquip));
			}	
		}
	}
}
