package znick_.riskofrain2.api.ror.buff;

/**
 * Enum of different stats saved to the player. Used to prevent item effects from multiplying instead of adding.
 * 
 * @author zNick_
 */
public enum PlayerStat {
	
	ARMOR(0),
	DAMAGE_MULTIPLIER(1),
	CRIT_CHANCE(0),
	LUCK(0),
	MAXIMUM_HEALTH_MULTIPLIER(1),
	MOVEMENT_SPEED_MULTIPLIER(1),
	FLAT_REGEN_SPEED(0),
	REGEN_SPEED_MULTIPLIER(1);

	private final double defaultValue;
	
	private PlayerStat(double defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public double getDefaultValue() {
		return this.defaultValue;
	}
}
