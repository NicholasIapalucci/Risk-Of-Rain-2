package znick_.riskofrain2.api.ror.survivor.ability;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.SurvivorEventHandler;
import znick_.riskofrain2.api.ror.survivor.ability.phase.AbilityPhase;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public abstract class Ability {
	
	private static final Map<Integer, Ability> ABILITIES = new TreeMap<>();
	private static int lastID;
	
	private final int id;
	private final Set<AbilityPhase> phases = new LinkedHashSet<AbilityPhase>();
	private final AbilityType type;
	private final String name;
	private final int baseCooldown;
	private final ResourceLocation texture;
	private int cooldown;
	
	public Ability(Survivor survivor, AbilityType type, String name, int baseCooldown) {
		this.id = getNextAvailableID();
		this.type = type;
		this.name = name;
		this.baseCooldown = baseCooldown;
		this.cooldown = baseCooldown;
		this.texture = RiskOfRain2Resources.get(RiskOfRain2Resources.GUI + "survivor/" + survivor.getName().toLowerCase() + "/abilities/" + name);
	}
	
	protected void addPhase(AbilityPhase phase) {
		this.phases.add(phase);
	}
	
	public void activate(EntityPlayer player) {
		SurvivorEventHandler.scheduleAbility(this);
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
	
	public ResourceLocation getTexture() {
		return this.texture;
	}
	
	private static boolean isPrime(int n) {
	    for(int i = 2; i <= Math.sqrt(n); i++) if(n % i == 0) return false;
	    return true;
	}

	/**
	 * Returns the unique ID associated with this ability. All IDs are prime so that
	 * loadouts can multiply them to create a unique ID for each loadout.
	 */
	public int getUniqueID() {
		return this.id;
	}
}
