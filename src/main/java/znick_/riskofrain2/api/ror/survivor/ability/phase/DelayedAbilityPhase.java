package znick_.riskofrain2.api.ror.survivor.ability.phase;

public interface DelayedAbilityPhase {
	public abstract int getTickDelay();
	
	default boolean shouldActivate(int startTick, int currentTick) {
		return currentTick > startTick + this.getTickDelay();
	}
}
