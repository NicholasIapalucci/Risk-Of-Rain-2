package znick_.riskofrain2.api.ror.items.equipment;

import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;

public class BlastShowerItem extends RiskOfRain2Equipment {

	protected BlastShowerItem() {
		super("blast_shower");
	}

	@Override
	public void useEquipment(PlayerData player) {
		for (Buff buff : player.getBuffs()) if (buff.isDebuff()) player.removeBuff(buff);
	}

	@Override
	public String getDescription() {
		return "Cleanse all negative effects";
	}

}
