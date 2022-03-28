package znick_.riskofrain2.api.ror.survivor.ability;

public class Loadout {

	private Ability primary;
	private Ability secondary;
	private Ability utility;
	private Ability special;
	private int uniqueID;
	
	public Loadout(Ability primary, Ability secondary, Ability utility, Ability special) {
		this.primary = primary;
		this.secondary = secondary;
		this.utility = utility;
		this.special = special;
		this.generateUniqueID();
	}
	
	private void generateUniqueID() {
	}
	
	public Ability getUtility() {
		return this.utility;
	}

	public Ability getSpecial() {
		return this.special;
	}
}
