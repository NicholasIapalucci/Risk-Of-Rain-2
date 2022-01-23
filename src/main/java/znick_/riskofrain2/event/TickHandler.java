package znick_.riskofrain2.event;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.ror.survivor.ability.phase.AbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.DelayedAbilityPhase;
import znick_.riskofrain2.event.handler.EventHandler;

public class TickHandler extends EventHandler {

	private static int serverTick = 0;
	private static int clientTick = 0;

	/**
	 * Called when the server ticks. Typically 20 ticks a second.
	 * 
	 * @param event The {@code TickEvent} to listen for.
	 */
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) serverTick++;
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) clientTick++;		
	}

	public static int server() {
		return serverTick;
	}
	
	public static int client() {
		return clientTick;
	}

	public static float toSeconds(int tick) {
		return ((float) tick) / 20f;
	}

	public static int fromSeconds(int seconds) {
		return seconds * 20;
	}
}
