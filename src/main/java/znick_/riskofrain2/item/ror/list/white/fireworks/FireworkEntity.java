package znick_.riskofrain2.item.ror.list.white.fireworks;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import znick_.riskofrain2.entity.util.HomingProjectile;

/**
 * The firework entity for the Risk of Rain 2 fireworks item. The entity is a {@link HomingProjectile}.
 * 
 * @author zNick_
 */
public class FireworkEntity extends HomingProjectile {

	public FireworkEntity(World world) {
		super(world);
		this.init();
	}
	
	public FireworkEntity(World world, double x, double y, double z) {
		super(world, x, y, z);
		this.init();
	}
	
	private void init() {
		this.setVelocity(0, 0.5, 0);
		this.homingDelay = 10;
		this.rotationYaw = 180;
	}

	@Override
	protected void onCollisionWithTarget() {
		ObfuscationReflectionHelper.setPrivateValue(EntityLivingBase.class, this.target, 0, "lastDamage");
		this.target.attackEntityFrom(DamageSource.generic, 2);
		this.explode();
	}
	
	private void explode() {
		this.worldObj.spawnParticle("fireworkSpark", this.posX, this.posY, this.posZ, 0, 0, 0);
	}

}
