package znick_.riskofrain2.api.ror.artifact.list.command.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class CommandEssenceCube extends Entity implements IEntityAdditionalSpawnData {
	
	private ItemRarity rarity;
	private double size = Math.random() * 0.5;
	
	public CommandEssenceCube(World world) {
		super(world);
	}
	
	public CommandEssenceCube(CommandEssenceEntity entity) {
		this(entity.worldObj);
		this.setPosition(entity.posX + Math.random() - 0.5, entity.posY + Math.random(), entity.posZ + Math.random() - 0.5);
		this.rarity = entity.getRarity();
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
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = (NBTTagCompound) nbt.getTag("tag");
		this.rarity = ItemRarity.values()[tag.getInteger("rarity")];
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("rarity", this.getRarity().ordinal());
		nbt.setTag("tag", tag);
	}

	public double getSize() {
		return this.size;
	}

	public ItemRarity getRarity() {
		return this.rarity;
	}

	@Override
	public void writeSpawnData(ByteBuf buf) {
		buf.writeInt(this.rarity.ordinal());
	}

	@Override
	public void readSpawnData(ByteBuf buf) {
		this.rarity = ItemRarity.values()[buf.readInt()];
	}
}
