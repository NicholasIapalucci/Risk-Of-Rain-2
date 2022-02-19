package znick_.riskofrain2.api.ror.survivor.ability.phase;

import net.minecraft.entity.player.EntityPlayer;

public interface RepeatingAbilityPhase {
	
	/**
	 * Returns whether or not this phase is currently active and repeating. Used by the next phase when
	 * listening for key inputs to know if the player is actually in the ability.
	 */
	public abstract boolean isActive();
	
	/**
	 * Called once when the ability first activates and none of the time when it is repeated. Used
	 * to play sounds and such. 
	 */
	public default void activateFirst(EntityPlayer player) {}
}
