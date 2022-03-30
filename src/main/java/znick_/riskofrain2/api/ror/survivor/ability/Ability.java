package znick_.riskofrain2.api.ror.survivor.ability;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.SurvivorEventHandler;
import znick_.riskofrain2.api.ror.survivor.ability.phase.AbilityPhase;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;
import znick_.riskofrain2.util.helper.MathHelper;

public abstract class Ability<T extends Ability> {
	
	private static final Map<Integer, Ability> ABILITIES = new TreeMap<>();
	private static int lastID;
	
	private final int id;
	private final Set<AbilityPhase> phases = new LinkedHashSet<AbilityPhase>();
	private final AbilityType type;
	private final String name;
	private final int baseCooldown;
	private final ResourceLocation texture;
	private int cooldown;
	
	public Ability(Survivor survivor, AbilityType type, String name, int baseCooldown, boolean isMainInstance) {
		this.id = getNextAvailableID();
		this.type = type;
		this.name = name;
		this.baseCooldown = baseCooldown;
		this.cooldown = baseCooldown;
		this.texture = RiskOfRain2Resources.get(RiskOfRain2Resources.GUI + "survivor/" + survivor.getName().toLowerCase() + "/abilities/" + name);
		if (isMainInstance) ABILITIES.put(this.id, this);
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
		while(!MathHelper.isPrime(++lastID)) {}
	    return lastID;
	}
	
	public ResourceLocation getTexture() {
		return this.texture;
	}
	
	/**
	 * Returns the unique ID associated with this ability. All IDs are prime so that
	 * loadouts can multiply them to create a unique ID for each loadout.
	 */
	public int getUniqueID() {
		return this.id;
	}

	public static Ability fromID(int i) {
		return ABILITIES.get(i);
	}
	
	public abstract T newInstance();
}
