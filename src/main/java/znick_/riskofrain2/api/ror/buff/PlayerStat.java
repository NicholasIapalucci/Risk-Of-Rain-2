package znick_.riskofrain2.api.ror.buff;

public enum PlayerStat {
	
	ARMOR(0),
	DAMAGE_MULTIPLIER(1),
	CRIT_CHANCE(0),
	LUCK(0),
	MAXIMUM_HEALTH_MULTIPLIER(1),
	MOVEMENT_SPEED_MULTIPLIER(1);

	private final double defaultValue;
	
	private PlayerStat(double defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public double getDefaultValue() {
		return this.defaultValue;
	}
}
