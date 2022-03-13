package znick_.riskofrain2.item.ror.list.equipment;

import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;

/**
 * Class used for creating the {@link znick_.riskofrain2.item.RiskOfRain2Items#BLAST_SHOWER BLAST_SHOWER}
 * item.
 * 
 * @author zNick_
 */
public class BlastShowerItem extends RiskOfRain2Equipment {

	public BlastShowerItem() {
		super("blast_shower");
	}

	@Override
	public void useEquipment(PlayerData player) {
		for (Buff buff : player.getBuffs()) if (buff.isDebuff()) player.removeBuff(buff);
	}

	@Override
	public String getDescription() {
		return "Cleanse all negative effects.";
	}

}
