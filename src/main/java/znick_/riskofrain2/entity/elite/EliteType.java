package znick_.riskofrain2.entity.elite;

import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public enum EliteType {
	BLAZING,
	CELESTINE,
	GLACIAL,
	MALACHITE,
	MENDING,
	OVERLOADING,
	PERFECTED,
	VOIDTOUCHED;
	
	private RiskOfRain2Item item;
	
	public void setItem(RiskOfRain2Item item) {
		this.item = item;
	}
	
	public RiskOfRain2Item getItem() {
		return this.item;
	}
}
