package znick_.riskofrain2.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import znick_.riskofrain2.event.handler.EventHandler;

public class Tick extends EventHandler {

	private static int serverTick = 0;

	/**
	 * Called when the server ticks. Typically 20 ticks a second.
	 * 
	 * @param event The {@code TickEvent} to listen for.
	 */
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		serverTick++;
	}
	
	public static int server() {
		return serverTick;
	}
	
	public static float toSeconds(int tick) {
		return ((float) tick)/20f;
	}
	
	public static int fromSeconds(int seconds) {
		return seconds * 20;
	}
}
