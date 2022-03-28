package znick_.riskofrain2.entity.inanimate;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class CommandEssenceEntity extends Entity {

	private ItemRarity rarity;
	private final CommandEssenceCube[] cubes = new CommandEssenceCube[15];
	
	public CommandEssenceEntity(World world) {
		super(world);
		for (int i = 0; i < this.cubes.length; i++) {
			CommandEssenceCube cube = new CommandEssenceCube(this);
			this.cubes[i] = cube;
			this.worldObj.spawnEntityInWorld(cube);
		}
		this.setSize(1, 1);
	}
	
	public CommandEssenceEntity(World world, double x, double y, double z, ItemRarity rarity) {
		this(world);
		this.setPosition(x + this.width/2, y, z + this.width/2);
		this.rarity = rarity;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.worldObj.isRemote) {
			for (int i = 0; i < this.cubes.length; i++) {
				if (this.cubes[i].isDead) {
					CommandEssenceCube cube = new CommandEssenceCube(this);
					this.cubes[i] = cube;
					this.worldObj.spawnEntityInWorld(cube);
				}
			}
		}
	}

	@Override
	protected void entityInit() {
		
	}
	
	@Override
	public void setDead() {
		for (CommandEssenceCube cube : this.cubes) cube.setDead();
		super.setDead();
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = (NBTTagCompound) nbt.getTag("tag");
		this.rarity = ItemRarity.values()[tag.getInteger("rarity")];
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("rarity", this.rarity.ordinal());
		nbt.setTag("tag", tag);
	}
	
	@Override
	public boolean canBeCollidedWith() {
	    return true;
	}

	public ItemRarity getRarity() {
		return this.rarity;
	}

}
