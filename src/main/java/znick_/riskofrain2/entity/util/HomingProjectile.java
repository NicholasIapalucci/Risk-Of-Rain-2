package znick_.riskofrain2.entity.util;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.util.helper.MathHelper;

public class HomingProjectile extends Entity implements IProjectile {

	protected EntityLiving target;
	private final double speed;
	private final int searchRadius;
	private static final double DEFAULT_SPEED = 0.2;
	
	protected int lifespan = TickHandler.fromSeconds(10);
	protected int ticksExisted;
	protected int homingDelay;
	
	public HomingProjectile(World world) {
		super(world);
		this.searchRadius = 25;
		this.target = this.findTarget();
		this.speed = DEFAULT_SPEED;
	}
	
	public HomingProjectile(World world, double x, double y, double z) {
		super(world);
		this.setPosition(x, y, z);
		this.searchRadius = 25;
		this.target = this.findTarget();
		this.speed = DEFAULT_SPEED;
	}
	
	public HomingProjectile(World world, int searchRadius, double speed) {
		super(world);
		this.searchRadius = searchRadius;
		this.target = this.findTarget();
		this.speed = speed;
	}
	
	public HomingProjectile(World world, EntityLiving target, double speed) {
		super(world);
		this.searchRadius = 25;
		this.target = target;
		this.speed = speed;
	}
	
	@Override
	public void onUpdate() {
		System.out.println(this.motionY);
		super.onUpdate();
		this.ticksExisted++;
		if (this.isDead) return;
		
		// If it isn't homing yet, update its position normally with its velocity
		if (this.ticksExisted < this.homingDelay) {
			this.posX += this.motionX;
	        this.posY += this.motionY;
	        this.posZ += this.motionZ;
	        return;
		}
		
		// Otherwise, make sure that there is an alive target to home towards
		if (this.target != null && !this.target.isDead) {
			this.setVelocityTowardsTarget();
			AxisAlignedBB bb = this.target.boundingBox;
			double distance = this.getDistance(this.target.posX, this.target.posY + (bb.maxY - bb.minY)/2, this.target.posZ);
			if (distance < 0.1) {
				this.onCollisionWithTarget();
				this.setDead();
			}
		} 
		
		else {
			this.target = this.findTarget();
		}
		
		// If no target was found or hit within the lifespan, just kill the projectile
		if (this.ticksExisted > this.lifespan) {
			this.setDead();
		}
    }
	
	/**
	 * Called when this projectile hits its target. Usually used to hurt the target etc. Subclasses
	 * needn't call {@link #setDead()} in this method because that is done automatically afterward.
	 */
	protected void onCollisionWithTarget() {}
	
	/**
	 * Sets the velocity of this homing projectile to be moving towards its target entity.
	 */
	private void setVelocityTowardsTarget() {
		Vec3 thisVector = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		AxisAlignedBB bb = this.target.boundingBox;
		Vec3 targetVector = Vec3.createVectorHelper(this.target.posX, this.target.posY + (bb.maxY - bb.minY)/2, this.target.posZ);
		Vec3 between = thisVector.subtract(targetVector);
		Vec3 newThisVec = MathHelper.addVectors(thisVector, MathHelper.multiplyVectorByScalar(between.normalize(), this.speed));
		this.setPosition(newThisVec.xCoord, newThisVec.yCoord, newThisVec.zCoord);
		this.updateHeading();
	}
	
	/**
	 * Updates the heading so that this projectile points towards its target
	 */
	private void updateHeading() {
		double dx = this.target.posX - this.posX;
		double dy = this.target.boundingBox.minY + ((double) target.height) - this.posY;
		double dz = this.target.posZ - this.posZ;
		this.setThrowableHeading(dx, dy, dz, 3.0F, 1.0F);
	}
	
	/**
	 * Finds the closest entity to this entity. Uses the {@link #searchRadius} field to determine how
	 * far away entities can be considered.
	 * 
	 * @return The nearest entity within the {@link #searchRadius}, or {@code null} if no such entity is
	 * within that radius.
	 */
	private EntityLiving findTarget() {
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(
			this.posX - this.searchRadius, 
			this.posY - this.searchRadius, 
			this.posZ - this.searchRadius, 
			this.posX + this.searchRadius, 
			this.posY + this.searchRadius, 
			this.posZ + this.searchRadius
		);
		List<EntityLiving> possibleTargets = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, aabb);
		if (possibleTargets.isEmpty()) return null;
		EntityLiving target = possibleTargets.get(0);
		for (EntityLiving entity : possibleTargets) {
			if (entity.getDistanceToEntity(this) < target.getDistanceToEntity(this)) {
				target = entity;
			}
		}
		return target;
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		
	}
	
	@Override
	public void setThrowableHeading(double x, double y, double z, float p7, float p8) {
		float f2 = (float) Math.sqrt(x * x + y * y + z * z);
        
		x /= (double) f2;
        y /= (double) f2;
        z /= (double) f2;
        
        x += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) p8;
        y += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) p8;
        z += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) p8;
        
        x *= (double) p7;
        y *= (double) p7;
        z *= (double) p7;
        
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        
        float f3 = (float) Math.sqrt(x * x + z * z);
        
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, (double) f3) * 180.0D / Math.PI);
	}

}
