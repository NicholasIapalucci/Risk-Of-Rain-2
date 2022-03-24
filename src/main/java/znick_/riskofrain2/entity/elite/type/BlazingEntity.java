package znick_.riskofrain2.entity.elite.type;

import net.minecraft.entity.EntityLivingBase;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.mc.data.NonPlayerEntityData;
import znick_.riskofrain2.entity.elite.EliteEntity;
import znick_.riskofrain2.entity.elite.EliteType;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public interface BlazingEntity extends EliteEntity {

	@Override
	public default EliteType getEliteType() {
		return EliteType.BLAZING;
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
