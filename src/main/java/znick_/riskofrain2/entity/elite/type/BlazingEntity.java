package znick_.riskofrain2.entity.elite.type;

import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import znick_.riskofrain2.entity.elite.EliteEntity;
import znick_.riskofrain2.entity.elite.EliteType;

public interface BlazingEntity extends EliteEntity {

	@Override
	public default EliteType getEliteType() {
		return EliteType.BLAZING;
	}
	
	@Override
	public default void onEntityCreation() {
		EliteEntity.super.onEntityCreation();
		EntityLiving entity = (EntityLiving) this;
		entity.addPotionEffect(new PotionEffect(Potion.fireResistance.id, Integer.MAX_VALUE, 0));
		entity.setFire(Integer.MAX_VALUE);
	}
	
	@Override
	public default void updateEliteEntity() {
		EntityLiving entity = (EntityLiving) this;
		if (!entity.worldObj.isRemote) {
			entity.worldObj.setBlock((int) entity.posX, (int) entity.posY, (int) entity.posZ, Blocks.fire);
		}
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
