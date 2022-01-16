package znick_.riskofrain2.event;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.ror.survivor.ability.phase.AbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.DelayedAbilityPhase;
import znick_.riskofrain2.event.handler.EventHandler;

public class TickHandler extends EventHandler {

	private static int serverTick = 0;

	private final static Map<Queue<AbilityPhase>, Map.Entry<EntityPlayer, Integer>> QUEUED_ABILITIES = new HashMap<>();
	private static final Map<AbilityPhase, EntityPlayer> REPEATING_PHASES = new HashMap<>();
	
	/**
	 * Called when the server ticks. Typically 20 ticks a second.
	 * 
	 * @param event The {@code TickEvent} to listen for.
	 */
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) serverTick++;
		
		//Loop through all scheduled abilities
		for (Map.Entry<Queue<AbilityPhase>, Map.Entry<EntityPlayer, Integer>> entry : QUEUED_ABILITIES.entrySet()) {
			
			//Retrieve the values
			Queue<AbilityPhase> queue = entry.getKey();
			EntityPlayer player = entry.getValue().getKey();
			AbilityPhase next = queue.peek();
			
			//Check if it's a delayed phase
			if (next instanceof DelayedAbilityPhase) {
				DelayedAbilityPhase delayedPhase = (DelayedAbilityPhase) next;
				int tickAtLastPhase = entry.getValue().getValue();
				int tickDelay = delayedPhase.getTickDelay();
				
				//Check if the delay has elapsed
				if (serverTick > tickAtLastPhase + tickDelay) {
					//If it has, get and remove the next phase
					AbilityPhase nextPhase = queue.remove();
					//Check if the player is currently in a phase
					for (Map.Entry<AbilityPhase, EntityPlayer> phaseEntry : REPEATING_PHASES.entrySet()) {
						if (phaseEntry.getValue() == player) {
							REPEATING_PHASES.remove(phaseEntry.getKey());
						}
					}
					//Activate the phase once
					nextPhase.activatePhase(player);
					//Set a new tickAtLastPhase
					QUEUED_ABILITIES.put(queue, new AbstractMap.SimpleEntry<>(player, serverTick));
				}
			}
			
			//If it's not delayed, activate it immediately. 
			else {
				AbilityPhase nextPhase = queue.remove();
				nextPhase.activatePhase(player);
			}
			
			//Remove the queue if it's empty
			if (queue.isEmpty()) {
				QUEUED_ABILITIES.remove(queue);
			}
		}
		
		for (Map.Entry<AbilityPhase, EntityPlayer> phaseEntry : REPEATING_PHASES.entrySet()) {
			phaseEntry.getKey().activatePhase(phaseEntry.getValue());
		}
	}
	
	public static void queueAbilityPhases(AbilityPhase[] phases, EntityPlayer player) {
		Queue<AbilityPhase> queue = new LinkedList<>();
		for (AbilityPhase phase : phases) queue.add(phase);
		QUEUED_ABILITIES.put(queue, new AbstractMap.SimpleEntry<>(player, serverTick));
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
