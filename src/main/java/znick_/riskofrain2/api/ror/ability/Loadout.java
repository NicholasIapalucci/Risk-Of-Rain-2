package znick_.riskofrain2.api.ror.ability;

public class Loadout {

	private UtilityAbility utility;
	private SpecialAbility special;
	
	public Loadout() {

	}
	
	public void setLoadoutFromIDs(int[] ids) {
		this.utility = (UtilityAbility) Ability.getAbilityFromID(ids[2]);
		this.special = (SpecialAbility) Ability.getAbilityFromID(ids[3]);
	}
	
	public void setUtilityAbility(UtilityAbility utility) {
		this.utility = utility;
	}
	
	public UtilityAbility getUtilityAbility() {
		return this.utility;
	}
	
	public void setSpecialAbility(SpecialAbility special) {
		this.special = special;
	}
	
	public SpecialAbility getSpecialAbility() {
		return this.special;
	}
}
