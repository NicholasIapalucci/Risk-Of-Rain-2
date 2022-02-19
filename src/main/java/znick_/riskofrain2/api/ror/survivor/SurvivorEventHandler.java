package znick_.riskofrain2.api.ror.survivor;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

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

	private static int lastAbilityTick;
	private static Queue<AbilityPhase> abilityQueue = new LinkedList<>();
	private static Optional<AbilityPhase> repeatingPhase = Optional.empty();

	@SubscribeEvent
	public void listenForAbilityActivation(KeyInputEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		for (Survivor survivor : Survivor.getSurvivors()) {
			if (survivor.isPlayer(player)) {
				PlayerData data = PlayerData.get(player);
				Loadout loadout = data.getLoadout();

				// If the utility key was pressed, activate the utility ability
				if (RiskOfRain2KeyBinds.UTILITY.getKeyBinding().isPressed()) {
					try {
						loadout.getUtility().newInstance().activate(player);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}

				// If the special key was pressed, activate the special ability
				if (RiskOfRain2KeyBinds.SPECIAL.getKeyBinding().isPressed()) {
					try {
						loadout.getSpecial().newInstance().activate(player);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}

				break;
			}
		}
	}

	@SubscribeEvent
	public void activateAbilities(TickEvent.ClientTickEvent event) {
		if (abilityQueue.isEmpty()) return;
		int clientTick = TickHandler.client();
		AbilityPhase next = abilityQueue.peek();

		// Check if it's a delayed phase
		if (next instanceof DelayedAbilityPhase) {
			DelayedAbilityPhase delayedPhase = (DelayedAbilityPhase) next;
			int tickDelay = delayedPhase.getTickDelay();

			// Check if the delay has elapsed
			if (delayedPhase.shouldActivate(lastAbilityTick, clientTick)) {
				// If it has, get and remove the next phase
				AbilityPhase nextPhase = abilityQueue.remove();
				// Stop the player from their repeating phase if they're in one.
				repeatingPhase = Optional.empty();
				// Activate the phase once
				nextPhase.activatePhase(Minecraft.getMinecraft().thePlayer);
				// Schedule it to repeat if it should
				if (next instanceof RepeatingAbilityPhase) repeatingPhase = Optional.of(next);
				// Set a new tickAtLastPhase
				lastAbilityTick = clientTick;
			}
		}

		// If it's not delayed, activate it immediately.
		else if (!(next instanceof ActivatedAbilityPhase)) {
			AbilityPhase nextPhase = abilityQueue.remove();
			if (nextPhase instanceof RepeatingAbilityPhase) repeatingPhase = Optional.of(nextPhase);
			nextPhase.activatePhase(Minecraft.getMinecraft().thePlayer);
		}

		// Activate all repeating phases
		if (repeatingPhase.isPresent()) repeatingPhase.get().activate();
	}

	/**
	 * Schedules an ability.
	 * 
	 * @param phases
	 * @param player
	 */
	public static void scheduleAbility(Ability ability) {
		abilityQueue.clear();
		for (AbilityPhase phase : ability.getPhases()) abilityQueue.add(phase);
		lastAbilityTick = TickHandler.client();
	}

	public static void activateRepeatingAbility(EntityPlayer player, RepeatingAbilityPhase next) {
		next.activateFirst(Minecraft.getMinecraft().thePlayer);
		repeatingPhase = Optional.of((AbilityPhase) next);
	}
	
	public static void deactivateRepeatingAbility() {
		repeatingPhase = Optional.empty();
	}
}
