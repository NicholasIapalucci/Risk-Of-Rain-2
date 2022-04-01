package znick_.riskofrain2.item.ror.list.green.atgmissile;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import znick_.riskofrain2.entity.util.HomingProjectile;

public class ATGMissileEntity extends HomingProjectile {

	private int itemCount;
	
	public ATGMissileEntity(World world) {
		super(world);
	}

	public ATGMissileEntity(World world, double x, double y, double z, int itemCount) {
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
		ObfuscationReflectionHelper.setPrivateValue(Entity.class, this.target, false, "invulnerable");
		this.target.attackEntityFrom(DamageSource.generic, this.itemCount * 6);
	}
}
