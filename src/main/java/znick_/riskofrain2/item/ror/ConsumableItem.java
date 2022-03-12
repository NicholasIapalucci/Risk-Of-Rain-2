package znick_.riskofrain2.item.ror;

import znick_.riskofrain2.api.mc.data.PlayerData;

public interface ConsumableItem {
	public abstract void consume(PlayerData player);
	public abstract RiskOfRain2Item getBrokenItem();
}
