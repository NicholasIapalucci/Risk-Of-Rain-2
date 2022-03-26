package znick_.riskofrain2.entity.elite.mobs.zombie;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import znick_.riskofrain2.entity.elite.type.GlacialEntity;

public class GlacialZombie extends EntityZombie implements GlacialEntity {

	public GlacialZombie(World world) {
		super(world);
		this.setCustomNameTag("Glacial Zombie");
	}
	
	public GlacialZombie(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y, z);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.updateEliteEntity();
	}

	@Override
	public void updateEliteEntity() {}
}
