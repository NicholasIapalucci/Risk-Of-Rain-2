package znick_.riskofrain2.api.ror.survivor.ability;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.phase.AbilityPhase;
import znick_.riskofrain2.event.TickHandler;

public abstract class Ability {
	
	private static final Map<Integer, Ability> ABILITIES = new TreeMap<>();
	private static int lastID;
	
	private final int id;
	private final Set<AbilityPhase> phases = new LinkedHashSet<AbilityPhase>();
	private final AbilityType type;
	private final String name;
	private final int baseCooldown;
	private int cooldown;
	
	public Ability(Survivor survivor, AbilityType type, String name, int baseCooldown) {
		this.id = getNextAvailableID();
		this.type = type;
		this.name = name;
		this.baseCooldown = baseCooldown;
		this.cooldown = baseCooldown;
	}
	
	protected void addPhase(AbilityPhase phase) {
		this.phases.add(phase);
	}
	
	public void activate(EntityPlayer player) {
		TickHandler.queueAbilityPhases(this.getPhases(), player);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getBaseCooldown() {
		return this.baseCooldown;
	}
	
	public AbilityType getAbilityType() {
		return this.type;
	}
	
	public AbilityPhase[] getPhases() {
		return this.phases.toArray(new AbilityPhase[0]);
	}
	
	private static int getNextAvailableID() {
		while(!isPrime(++lastID)) {}
	    return lastID;
	}
	
	private static boolean isPrime(int n) {
	    for(int i = 2; i <= Math.sqrt(n); i++) if(n % i == 0) return false;
	    return true;
	}

	public int getID() {
		return this.id;
	}
}
