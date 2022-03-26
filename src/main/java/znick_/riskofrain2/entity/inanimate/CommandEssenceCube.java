package znick_.riskofrain2.entity.inanimate;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CommandEssenceCube extends Entity {
	
	private CommandEssenceEntity commandEssence;
	private double size = Math.random() * 0.5;
	
	public CommandEssenceCube(World world) {
		super(world);
	}
	
	public CommandEssenceCube(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x + Math.random() - 0.5, y + Math.random(), z + Math.random() - 0.5);
	}
	
	public CommandEssenceCube(CommandEssenceEntity entity) {
		this(entity.worldObj, entity.posX, entity.posY, entity.posZ);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.size -= 0.01;
		this.setSize((float) this.size, (float) this.size); 
		if (this.size <= 0) this.setDead();
		
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		
	}

	public double getSize() {
		return this.size;
	}
}
