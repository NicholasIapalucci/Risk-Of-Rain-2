package znick_.riskofrain2.client.keybind;

import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.AbilityType;

public class AbilityAction implements PlayerAction {

	private final AbilityType abilityType;
	
	public AbilityAction(AbilityType abilityType) {
		this.abilityType = abilityType;
	}
	
	@Override
	public void run(PlayerData player) {
		if (Survivor.fromPlayer(player.getEntity()).isPresent()) {
			player.getLoadout().getAbility(this.abilityType).newInstance().activate(player.getEntity());
		}
	}

}
