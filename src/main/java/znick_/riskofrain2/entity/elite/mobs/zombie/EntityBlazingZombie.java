package znick_.riskofrain2.entity.elite.mobs.zombie;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import znick_.riskofrain2.entity.elite.type.BlazingEntity;

public class EntityBlazingZombie extends EntityZombie implements BlazingEntity {

	public EntityBlazingZombie(World world) {
		super(world);
		this.setCustomNameTag("Blazing Zombie");
	}
	
	public EntityBlazingZombie(World world, int x, int y, int z) {
		super(world);
		this.setPosition(x, y, z);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.updateEliteEntity();
	}

}
