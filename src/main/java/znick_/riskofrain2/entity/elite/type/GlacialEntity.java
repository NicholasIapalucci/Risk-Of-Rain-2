package znick_.riskofrain2.entity.elite.type;

import znick_.riskofrain2.entity.elite.EliteEntity;
import znick_.riskofrain2.entity.elite.EliteType;

public interface GlacialEntity extends EliteEntity {

	@Override
	public default EliteType getEliteType() {
		return EliteType.GLACIAL;
	}
	
	@Override
	public default int getHealthMultiplier() {
		return 4;
	}
	
	@Override
	public default int getDamageMultiplier() {
		return 2;
	}
}
