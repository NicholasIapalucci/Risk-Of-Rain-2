package znick_.riskofrain2.api.ror.items.equipment.jadeelephant;

import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.equipment.RiskOfRain2Equipment;

public class JadeElephantItem extends RiskOfRain2Equipment {

	public JadeElephantItem() {
		super("jade_elephant");
	}

	@Override
	public void useEquipment(PlayerData player) {
		player.addBuff(new JadeElephantBuff());
	}

	@Override
	public String getDescription() {
		return "Gain massive armor for 5 seconds";
	}
	
	

}
