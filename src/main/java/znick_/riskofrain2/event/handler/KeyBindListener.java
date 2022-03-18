package znick_.riskofrain2.event.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.client.gui.logbook.LogbookGui;
import znick_.riskofrain2.client.keybind.RiskOfRain2KeyBinds;

public class KeyBindListener extends EventHandler {

	@SubscribeEvent
	public void onKeyPress(KeyInputEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if (RiskOfRain2KeyBinds.LOGBOOK.getKeyBinding().isPressed()) {
			player.openGui(RiskOfRain2Mod.instance, LogbookGui.GUI_ID, Minecraft.getMinecraft().theWorld, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
	}
}
