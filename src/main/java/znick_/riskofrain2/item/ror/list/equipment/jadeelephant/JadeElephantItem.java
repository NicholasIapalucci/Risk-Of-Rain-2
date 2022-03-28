package znick_.riskofrain2.item.ror.list.equipment.jadeelephant;

import java.util.Arrays;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.list.equipment.RiskOfRain2Equipment;

public class JadeElephantItem extends RiskOfRain2Equipment {

	public JadeElephantItem() {
		super("jade_elephant");
	}

	@Override
	public void activateEffect(AbstractEntityData player) {
		player.addBuff(new JadeElephantBuff(1));
	}

	@Override
	public String getDescription() {
		return "Gain massive armor for 5 seconds";
	}
	
	

}
