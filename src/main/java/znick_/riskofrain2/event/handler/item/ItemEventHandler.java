package znick_.riskofrain2.event.handler.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.client.gui.GuiOverlay;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.event.handler.character.HuntressEventHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.helper.InventoryHelper;

public class ItemEventHandler extends EventHandler {
	
	@SubscribeEvent
	public void procSoldierSyringe(BreakSpeed event) {
		if (event.entityPlayer != null) {
			int soldierSyringeAmount = InventoryHelper.amountOfItems(event.entityPlayer, RiskOfRain2Items.SOLDIER_SYRINGE);
			event.newSpeed = (float) (1 + (0.15 * soldierSyringeAmount));
		}
	}
}
