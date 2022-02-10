package znick_.riskofrain2.api.ror.survivor;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.Loadout;
import znick_.riskofrain2.api.ror.survivor.ability.phase.AbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.ActivatedAbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.DelayedAbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.RepeatingAbilityPhase;
import znick_.riskofrain2.client.keybind.RiskOfRain2KeyBinds;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.event.handler.TickHandler;

public class SurvivorEventHandler extends EventHandler {

	private final static Map<EntityPlayer, Map.Entry<Queue<AbilityPhase>, Integer>> QUEUED_ABILITIES = new HashMap<>();
	private static final Map<EntityPlayer, AbilityPhase> REPEATING_PHASES = new HashMap<>();
	
	@SubscribeEvent
	public void listenForAbilityActivation(KeyInputEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		for (Survivor survivor : Survivor.getSurvivors()) {
			if (survivor.isPlayer(player)) {
				PlayerData data = PlayerData.get(player);
				Loadout loadout = data.getLoadout();
				
				//If the utility key was pressed, activate the utility ability
				if (RiskOfRain2KeyBinds.UTILITY.getKeyBinding().isPressed()) {
					try {loadout.getUtility().newInstance().activate(player);}
					catch(Exception e) {throw new RuntimeException(e);}
				}
				
				// If the special key was pressed, activate the special ability
				if (RiskOfRain2KeyBinds.SPECIAL.getKeyBinding().isPressed()) {
					try {loadout.getSpecial().newInstance().activate(player);}
					catch(Exception e) {throw new RuntimeException(e);}
				}
				
				break;
			}
		}
	}
	
	@SubscribeEvent
	public void activateAbilities(TickEvent.ClientTickEvent event) {
		int clientTick = TickHandler.client();
		
		// Copy the set to prevent ConcurrentModificationExceptions
		Set<Map.Entry<EntityPlayer, Map.Entry<Queue<AbilityPhase>, Integer>>> entrySet = new LinkedHashSet<>(QUEUED_ABILITIES.entrySet());

		// Loop through all scheduled abilities
		for (Map.Entry<EntityPlayer, Map.Entry<Queue<AbilityPhase>, Integer>> entry : entrySet) {

			// Retrieve the values
			Queue<AbilityPhase> queue = entry.getValue().getKey();
			EntityPlayer player = entry.getKey();
			AbilityPhase next = queue.peek();

			// Remove the queue if it's empty
			if (next == null) {
				QUEUED_ABILITIES.remove(player);
				continue;
			}

			// Check if it's a delayed phase
			if (next instanceof DelayedAbilityPhase) {
				DelayedAbilityPhase delayedPhase = (DelayedAbilityPhase) next;
				int tickAtLastPhase = entry.getValue().getValue();
				int tickDelay = delayedPhase.getTickDelay();

				// Check if the delay has elapsed
				if (clientTick > tickAtLastPhase + tickDelay) {
					// If it has, get and remove the next phase
					AbilityPhase nextPhase = queue.remove();
					// Check if the player is currently in a phase
					for (Map.Entry<EntityPlayer, AbilityPhase> phaseEntry : REPEATING_PHASES.entrySet()) {
						if (phaseEntry.getValue() == player) REPEATING_PHASES.remove(phaseEntry.getKey());
					}
					// Activate the phase once
					nextPhase.activatePhase(player);
					// Schedule it to repeat if it should
					if (next instanceof RepeatingAbilityPhase) REPEATING_PHASES.put(player, next);
					// Set a new tickAtLastPhase
					QUEUED_ABILITIES.put(player, new AbstractMap.SimpleEntry<>(queue, clientTick));
				}
			}

			// If it's not delayed, activate it immediately.
			else if (!(next instanceof ActivatedAbilityPhase)) {		
				AbilityPhase nextPhase = queue.remove();
				if (nextPhase instanceof RepeatingAbilityPhase) REPEATING_PHASES.put(player, next);
				nextPhase.activatePhase(player);
			}
		}

		// Activate all repeating phases
		for (Map.Entry<EntityPlayer, AbilityPhase> phaseEntry : REPEATING_PHASES.entrySet()) {
			phaseEntry.getValue().activatePhase(phaseEntry.getKey());
		}
	}

	/**
	 * Schedules an ability.
	 * 
	 * @param phases
	 * @param player
	 */
	public static void scheduleAbility(Ability ability, EntityPlayer player) {
		Queue<AbilityPhase> queue = new LinkedList<>();
		for (AbilityPhase phase : ability.getPhases()) queue.add(phase);
		QUEUED_ABILITIES.put(player, new AbstractMap.SimpleEntry<>(queue, TickHandler.client()));
	}
	
	public static void activateRepeatingAbility(EntityPlayer player, AbilityPhase next) {
		REPEATING_PHASES.put(player, next);
	}
	
	public static void removeScheduledAbility(EntityPlayer player) {
		QUEUED_ABILITIES.remove(player);
		REPEATING_PHASES.remove(player);
	}
}
