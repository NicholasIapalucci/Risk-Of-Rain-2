package znick_.riskofrain2.api.ror.survivor.huntress.ability.primary;

import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.AbilityType;
import znick_.riskofrain2.event.handler.TickHandler;

public class StrafeAbility extends Ability<StrafeAbility> {

	public static final StrafeAbility MAIN_INSTANCE = new StrafeAbility(true);
	
	public StrafeAbility() {
		this(false);
	}
	
	private StrafeAbility(boolean isMainInstance) {
		super(Survivor.HUNTRESS, AbilityType.PRIMARY, "strafe", TickHandler.fromSeconds(0.5), isMainInstance);
	}

	@Override
	public StrafeAbility newInstance() {
		return new StrafeAbility();
	}

}
