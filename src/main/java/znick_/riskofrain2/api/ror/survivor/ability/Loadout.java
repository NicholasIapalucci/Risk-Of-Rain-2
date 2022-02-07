package znick_.riskofrain2.api.ror.survivor.ability;

public class Loadout {

	private Class<? extends Ability> primary;
	private Class<? extends Ability> secondary;
	private Class<? extends Ability> utility;
	private Class<? extends Ability> special;
	private int uniqueID;
	
	public Loadout(Class<? extends Ability> primary, Class<? extends Ability> secondary, Class<? extends Ability> utility, Class<? extends Ability> special) {
		this.primary = primary;
		this.secondary = secondary;
		this.utility = utility;
		this.special = special;
		this.generateUniqueID();
	}
	
	private void generateUniqueID() {
		
	}
	
	public Class<? extends Ability> getUtility() {
		return this.utility;
	}

	public Class<? extends Ability> getSpecial() {
		return this.special;
	}
}
