package znick_.riskofrain2.event.handler.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import znick_.riskofrain2.api.ror.character.PlayableCharacter;
import znick_.riskofrain2.client.gui.GuiOverlay;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.event.handler.character.HuntressEventHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.helper.InventoryHelper;

public class ItemEventHandler extends EventHandler {

	int tick = 0;
	EntityPlayer player;
	
	@SubscribeEvent
	public void renderGui(RenderGameOverlayEvent.Post event) {
		if (event.type != ElementType.EXPERIENCE) return;
		if (PlayableCharacter.isPlayerSurvivor(Minecraft.getMinecraft().thePlayer, PlayableCharacter.HUNTRESS)) {		
			
			HuntressEventHandler.wrappedUtility.setReady(HuntressEventHandler.canBlink);
			HuntressEventHandler.wrappedUtility.setCooldown((HuntressEventHandler.utilityTick + HuntressEventHandler.utilityCooldown - tick)/20 + 1);
			HuntressEventHandler.wrappedUtility.setCharges(HuntressEventHandler.utilitiesLeft);
			
			HuntressEventHandler.wrappedSpecial.setReady(HuntressEventHandler.canArrowRain);
			HuntressEventHandler.wrappedSpecial.setCooldown((HuntressEventHandler.specialTick + HuntressEventHandler.specialCooldown - tick)/20 + 1);
			HuntressEventHandler.wrappedSpecial.setCharges(1);
			
			new GuiOverlay(Minecraft.getMinecraft(), this.player, PlayableCharacter.HUNTRESS, HuntressEventHandler.wrappedUtility, HuntressEventHandler.wrappedSpecial);
		}
	}
	
	@SubscribeEvent
	public void procSoldierSyringe(BreakSpeed event) {
		if (event.entityPlayer != null) {
			int soldierSyringeAmount = InventoryHelper.amountOfItems(event.entityPlayer, RiskOfRain2Items.SOLDIER_SYRINGE);
			event.newSpeed = (float) (1 + (0.15 * soldierSyringeAmount));
		}
	}
}
