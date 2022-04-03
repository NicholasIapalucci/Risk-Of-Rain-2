package znick_.riskofrain2.api.ror.survivor.huntress.ability.secondary;

import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.AbilityType;
import znick_.riskofrain2.event.handler.TickHandler;

public class LaserGlaiveAbility extends Ability<LaserGlaiveAbility> {

	public static final LaserGlaiveAbility MAIN_INSTANCE = new LaserGlaiveAbility(true);
	
	public LaserGlaiveAbility() {
		this(false);
	}
	
	private LaserGlaiveAbility(boolean isMainInstance) {
		super(Survivor.HUNTRESS, AbilityType.SECONDARY, "laser_glaive", TickHandler.fromSeconds(7), isMainInstance);
	}

	@Override
	public LaserGlaiveAbility newInstance() {
		return new LaserGlaiveAbility();
	}

}
