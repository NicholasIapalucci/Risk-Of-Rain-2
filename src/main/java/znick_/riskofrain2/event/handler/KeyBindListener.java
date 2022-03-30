package znick_.riskofrain2.event.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.client.keybind.RiskOfRain2KeyBinds;

public class KeyBindListener extends EventHandler {

	@SubscribeEvent
	public void onKeyPress(KeyInputEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		for (RiskOfRain2KeyBinds keybind : RiskOfRain2KeyBinds.values()) {
			if (keybind.getKeyBinding().isPressed()) keybind.activate(player);
		}
	}
}
