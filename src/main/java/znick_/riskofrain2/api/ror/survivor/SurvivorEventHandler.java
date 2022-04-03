package znick_.riskofrain2.api.ror.survivor;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.phase.AbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.ActivatedAbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.DelayedAbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.RepeatingAbilityPhase;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.event.handler.TickHandler;

public class SurvivorEventHandler extends EventHandler {

	private static int lastAbilityTick;
	private static final Queue<AbilityPhase> ABILITY_QUEUE = new LinkedList<>();
	private static Map.Entry<RepeatingAbilityPhase, EntityPlayer> repeatingPhase;
	private static int repeatingPhaseStartTick = 0;

	@SubscribeEvent
	public void activateAbilities(TickEvent.ClientTickEvent event) {
		if (ABILITY_QUEUE.isEmpty()) return;
		int clientTick = TickHandler.client();
		AbilityPhase next = ABILITY_QUEUE.peek();

		// Check if it's a delayed phase
		if (next instanceof DelayedAbilityPhase) {
			DelayedAbilityPhase delayedPhase = (DelayedAbilityPhase) next;
			int tickDelay = delayedPhase.getTickDelay();

			// Check if the delay has elapsed
			if (delayedPhase.shouldActivate(lastAbilityTick, clientTick)) {
				// If it has, get and remove the next phase
				AbilityPhase nextPhase = ABILITY_QUEUE.remove();
				// Stop the player from their repeating phase if they're in one.
				repeatingPhase = null;
				// Activate the phase once
				nextPhase.activatePhase(Minecraft.getMinecraft().thePlayer);
				// Schedule it to repeat if it should
				if (next instanceof RepeatingAbilityPhase) {
					repeatingPhase = new AbstractMap.SimpleEntry(next, Minecraft.getMinecraft().thePlayer);
					repeatingPhaseStartTick = clientTick;
				}
				// Set a new tickAtLastPhase
				lastAbilityTick = clientTick;
			}
		}

		// If it's not delayed, activate it immediately.
		else if (!(next instanceof ActivatedAbilityPhase)) {
			AbilityPhase nextPhase = ABILITY_QUEUE.remove();
			if (nextPhase instanceof RepeatingAbilityPhase) repeatingPhase = new AbstractMap.SimpleEntry(nextPhase, Minecraft.getMinecraft().thePlayer);
			nextPhase.activatePhase(Minecraft.getMinecraft().thePlayer);
		}

		// Activate all repeating phases
		if (repeatingPhase != null) {
			((AbilityPhase) repeatingPhase.getKey()).activatePhase(repeatingPhase.getValue());
			//if (repeatingPhase.getKey().getDuration() < repeatingPhaseStartTick + TickHandler.client());
		}
	}

	/**
	 * Schedules an ability.
	 * 
	 * @param ability The ability to schedule
	 */
	public static void scheduleAbility(Ability ability) {
		ABILITY_QUEUE.clear();
		for (AbilityPhase phase : ability.getPhases()) ABILITY_QUEUE.add(phase);
		lastAbilityTick = TickHandler.client();
	}

	public static void activateRepeatingAbility(EntityPlayer player, RepeatingAbilityPhase next) {
		next.activateFirst(Minecraft.getMinecraft().thePlayer);
		repeatingPhase = new AbstractMap.SimpleEntry(next, player);
		repeatingPhaseStartTick = TickHandler.server();
	}
	
	public static void deactivateRepeatingAbility() {
		repeatingPhase = null;
	}
}
