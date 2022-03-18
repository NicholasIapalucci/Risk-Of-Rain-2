package znick_.riskofrain2.client.gui;

import cpw.mods.fml.common.network.NetworkRegistry;
import znick_.riskofrain2.RiskOfRain2Mod;

public class RiskOfRain2Guis {

	public static void registerGuis() {
		NetworkRegistry.INSTANCE.registerGuiHandler(RiskOfRain2Mod.instance, new GuiHandler());
	}
}
