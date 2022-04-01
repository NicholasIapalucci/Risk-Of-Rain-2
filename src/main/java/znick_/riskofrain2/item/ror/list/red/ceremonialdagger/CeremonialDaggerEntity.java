package znick_.riskofrain2.item.ror.list.red.ceremonialdagger;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import znick_.riskofrain2.entity.util.HomingProjectile;

public class CeremonialDaggerEntity extends HomingProjectile {
	
	private int itemCount;
	
	public CeremonialDaggerEntity(World world) {
		super(world);
		this.init();
	}
	
	public CeremonialDaggerEntity(World world, double x, double y, double z, int itemCount) {
		super(world, x, y, z);
		this.itemCount = itemCount;
		this.init();
	}
	
	private void init() {
		this.setVelocity(0, 0.5, 0);
		this.homingDelay = 10;
		this.rotationYaw = 0;
	}

	@Override
	protected void onCollisionWithTarget() {
		this.target.attackEntityFrom(DamageSource.generic, this.itemCount * 10);
	}
}
