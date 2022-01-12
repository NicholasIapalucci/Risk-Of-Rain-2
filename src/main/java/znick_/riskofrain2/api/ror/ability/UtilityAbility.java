package znick_.riskofrain2.api.ror.ability;

public class UtilityAbility extends Ability {

	public UtilityAbility(String name, int baseCooldown) {
		super(name, baseCooldown);
	}

	@Override
	public AbilityType getAbilityType() {
		return AbilityType.UTILITY;
	}
}
