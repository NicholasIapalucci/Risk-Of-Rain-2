package znick_.riskofrain2.api.mc;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import znick_.riskofrain2.util.misc.UniqueDamageSource;

/**
 * A {@code FriendlyExplosion} is an {@link Explosion} that does not harm the exploder and does not
 * break any blocks in the world. 
 * 
 * @author zNick_
 */
public class FriendlyExplosion extends Explosion {

	private int maxExplosionSize;
	private World world;
	private Map map;

	/**
	 * Creates a new {@code FriendlyExplosion}. 
	 * 
	 * @param world The world the explosion is in
	 * @param exploder The player who created the explosion
	 * @param x The x-coordinate of the explosion
	 * @param y The y-coordinate of the explosion
	 * @param z The z-coordinate of the explosion
	 * @param size The size/power of the explosion
	 */
	private FriendlyExplosion(World world, EntityLivingBase exploder, double x, double y, double z, float size) {
		super(world, exploder, x, y, z, size);
		this.getFields();
	}
	
	/**
	 * Creates a new {@code FriendlyExplosion} and explodes it on the given mob.
	 * 
	 * @param mob The mob to center the explosion on
	 * @param exploder The exploder who is to avoid harm
	 * @param power The power of the explosion
	 */
	public static void explodeMob(Entity mob, EntityLivingBase exploder, int power) {
		FriendlyExplosion explosion = new FriendlyExplosion(mob.worldObj, exploder, mob.posX, mob.posY + (mob.height/2), mob.posZ, power);
        explosion.isFlaming = false;
        explosion.isSmoking = true;
        if (ForgeEventFactory.onExplosionStart(mob.worldObj, explosion)) return;
        explosion.doExplosionA();
        if (exploder.worldObj.isRemote) explosion.doExplosionB(true);
	}

	
	/**
	 * Fetches the private fields with reflection.
	 */
	private void getFields() {
		try {
			Field maxSizeField = Explosion.class.getDeclaredField("field_77289_h");
			Field worldField = Explosion.class.getDeclaredField("worldObj");
			Field mapField = Explosion.class.getDeclaredField("field_77288_k");

			maxSizeField.setAccessible(true);
			worldField.setAccessible(true);
			mapField.setAccessible(true);

			this.maxExplosionSize = (Integer) maxSizeField.get(this);
			this.world = (World) worldField.get(this);
			this.map = (Map) mapField.get(this);

		} 
		
		catch (Exception e) {throw new RuntimeException(e);}
	}

	@Override
	public void doExplosionA() {
		float f = this.explosionSize;
		HashSet hashset = new HashSet();
		
		int i;
		int j;
		int k;
		
		double d5;
		double d6;
		double d7;

		this.explosionSize *= 2.0F;
		
		i = MathHelper.floor_double(this.explosionX - (double) this.explosionSize - 1.0D);
		j = MathHelper.floor_double(this.explosionX + (double) this.explosionSize + 1.0D);
		k = MathHelper.floor_double(this.explosionY - (double) this.explosionSize - 1.0D);
		
		int i2 = MathHelper.floor_double(this.explosionY + (double) this.explosionSize + 1.0D);
		int l = MathHelper.floor_double(this.explosionZ - (double) this.explosionSize - 1.0D);
		int j2 = MathHelper.floor_double(this.explosionZ + (double) this.explosionSize + 1.0D);
		
		List list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox((double) i, (double) k, (double) l, (double) j, (double) i2, (double) j2));
		ForgeEventFactory.onExplosionDetonate(this.world, this, list, this.explosionSize);
		Vec3 vec3 = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			if (entity == this.exploder) continue; //Do not knockback or damage the entity that caused the explosion
			double d4 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double) this.explosionSize;

			if (d4 <= 1.0D) {
				d5 = entity.posX - this.explosionX;
				d6 = entity.posY + (double) entity.getEyeHeight() - this.explosionY;
				d7 = entity.posZ - this.explosionZ;
				double d9 = (double) MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);

				if (d9 != 0.0D) {
					d5 /= d9;
					d6 /= d9;
					d7 /= d9;
					
					double d10 = (double) this.world.getBlockDensity(vec3, entity.boundingBox);
					double d11 = (1.0D - d4) * d10;
					
					entity.attackEntityFrom(new UniqueDamageSource((EntityPlayer) this.exploder), (float) ((int) ((d11 * d11 + d11) / 2.0D * 8.0D * (double) this.explosionSize + 1.0D)));
					double d8 = EnchantmentProtection.func_92092_a(entity, d11);
					
					entity.motionX += d5 * d8;
					entity.motionY += d6 * d8;
					entity.motionZ += d7 * d8;
				}
			}
		}

		this.explosionSize = f;
	}
	
	@Override
	public void doExplosionB(boolean flag) {
		Minecraft.getMinecraft().thePlayer.playSound("random.explode", 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
		Minecraft.getMinecraft().theWorld.spawnParticle("largeexplode", this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
    }

}
