package znick_.riskofrain2.item.ror.list.white.bustlingfungus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class BustlingFungusRing extends Entity {

	private final EntityPlayer player;
	
	public BustlingFungusRing(World world) {
		super(world);
		this.player = null;
	}
	
	public BustlingFungusRing(World world, EntityPlayer player) {
		super(world);
		this.player = player;
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

}
