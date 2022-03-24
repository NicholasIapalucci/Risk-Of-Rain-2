package znick_.riskofrain2.entity.elite.type;

import znick_.riskofrain2.entity.elite.EliteEntity;
import znick_.riskofrain2.entity.elite.EliteType;

public interface MalachiteEntity extends EliteEntity {

	@Override
	public default EliteType getEliteType() {
		return EliteType.MALACHITE;
	}
	
	@Override
	public default int getHealthMultiplier() {
		return 18;
	}
	
	@Override
	public default int getDamageMultiplier() {
		return 6;
	}
}
