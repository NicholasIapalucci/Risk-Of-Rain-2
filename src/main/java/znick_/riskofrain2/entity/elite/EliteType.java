package znick_.riskofrain2.entity.elite;

import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public enum EliteType {
	BLAZING(RiskOfRain2Items.IFRITS_DISTINCTION),
	CELESTINE,
	GLACIAL,
	MALACHITE,
	MENDING,
	OVERLOADING,
	PERFECTED,
	VOIDTOUCHED;
	
	private final RiskOfRain2Item item;
	
	private EliteType() {
		this.item = null;
	}
	
	private EliteType(RiskOfRain2Item item) {
		this.item = item;
	}
	
	public RiskOfRain2Item getItem() {
		return this.item;
	}
}
