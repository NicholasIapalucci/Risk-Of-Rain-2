package znick_.riskofrain2.api.ror.ability;

public class SpecialAbility extends Ability {

	public SpecialAbility(String name, int baseCooldown) {
		super(name, baseCooldown);
	}

	@Override
	public AbilityType getAbilityType() {
		return AbilityType.SPECIAL;
	}
	
}