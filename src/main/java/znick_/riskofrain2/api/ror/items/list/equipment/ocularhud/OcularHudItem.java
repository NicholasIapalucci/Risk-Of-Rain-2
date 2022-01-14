package znick_.riskofrain2.api.ror.items.list.equipment.ocularhud;

import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.list.equipment.RiskOfRain2Equipment;

public class OcularHudItem extends RiskOfRain2Equipment {

	public OcularHudItem() {
		super("ocular_hud");
	}

	@Override
	public void useEquipment(PlayerData player) {
		player.addBuff(new OcularHudBuff());
	}

	@Override
	public String getDescription() {
		return "Gain 100% Critical Strike Chance for 8 seconds";
	}

}
