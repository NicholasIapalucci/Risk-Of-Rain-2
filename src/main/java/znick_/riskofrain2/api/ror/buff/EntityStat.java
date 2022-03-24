package znick_.riskofrain2.api.ror.buff;

/**
 * Enum of different stats saved to the player. Used to prevent item effects from multiplying instead of adding.
 * 
 * @author zNick_
 */
public enum EntityStat {
	
	ARMOR(0),
	BARRIER(0),
	DAMAGE_MULTIPLIER(1),
	CRIT_CHANCE(0),
	LUCK(0),
	MAXIMUM_HEALTH_MULTIPLIER(1),
	MOVEMENT_SPEED_MULTIPLIER(1),
	REGEN_SPEED_MULTIPLIER(1),
	MAX_SHIELD(0),
	SHIELD(0),
	EQUIPMENT_COOLDOWN_REDUCTION_PERCENTAGE(0);

	private final double defaultValue;
	
	private EntityStat(double defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public double getDefaultValue() {
		return this.defaultValue;
	}
}
