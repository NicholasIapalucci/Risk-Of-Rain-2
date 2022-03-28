package znick_.riskofrain2.event.handler;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public abstract class EventHandler {

	/**
	 * Creates a new {@code EventHandler} and registers it on the forge event bus
	 * and the FML bus.
	 */
	public EventHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}
}
