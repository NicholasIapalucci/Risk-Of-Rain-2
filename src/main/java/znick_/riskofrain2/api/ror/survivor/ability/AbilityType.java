package znick_.riskofrain2.api.ror.survivor.ability;

import znick_.riskofrain2.client.keybind.RiskOfRain2KeyBinds;

public enum AbilityType {
	PRIMARY(RiskOfRain2KeyBinds.PRIMARY),
	SECONDARY(RiskOfRain2KeyBinds.SECONDARY),
	UTILITY(RiskOfRain2KeyBinds.UTILITY),
	SPECIAL(RiskOfRain2KeyBinds.SPECIAL);
	
	private final RiskOfRain2KeyBinds bind;
	
	private AbilityType(RiskOfRain2KeyBinds bind) {
		this.bind = bind;
	}

	public RiskOfRain2KeyBinds getKeyBinding() {
		return this.bind;
	}
}
