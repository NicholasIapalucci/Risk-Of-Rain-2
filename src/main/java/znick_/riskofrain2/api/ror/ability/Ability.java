package znick_.riskofrain2.api.ror.ability;

import java.util.ArrayList;
import java.util.List;

public abstract class Ability {
	
	private static final List<Ability> ABILITIES = new ArrayList<Ability>();
	
	private final String name;
	private final int baseCooldown;
	
	public Ability(String name, int baseCooldown) {	
		this.name = name;
		this.baseCooldown = baseCooldown;
		ABILITIES.add(this);
	}
	
	public abstract AbilityType getAbilityType();
	
	public String getName() {
		return this.name;
	}
	
	public int getBaseCooldown() {
		return this.baseCooldown;
	}
	
	public int getID() {
		return ABILITIES.indexOf(this);
	}
	
	public static Ability getAbilityFromID(int id) {
		return ABILITIES.get(id);
	}
	
	public enum AbilityType {
		PRIMARY,
		SECONDARY,
		UTILITY,
		SPECIAL;
	}
}
