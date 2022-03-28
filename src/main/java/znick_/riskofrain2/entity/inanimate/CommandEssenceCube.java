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
	
	public CommandEssenceCube(CommandEssenceEntity entity) {
		this(entity.worldObj);
		this.setPosition(entity.posX + Math.random() - 0.5, entity.posY + Math.random(), entity.posZ + Math.random() - 0.5);
		this.commandEssence = entity;
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

	public CommandEssenceEntity getCommandEssenceEntity() {
		return this.commandEssence;
	}
}
