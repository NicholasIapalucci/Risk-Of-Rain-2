package znick_.riskofrain2.item.ror.consume;

import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public interface ConsumableItem {
	public abstract void consume(PlayerData player);
	public abstract RiskOfRain2Item getBrokenItem();
}
