package znick_.riskofrain2.item.ror.list.white.warbanner;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import znick_.riskofrain2.api.mc.data.PlayerData;

public class WarbannerTileEntity extends TileEntity {

	private int level = 1;

	/**
	 * Updates the entity. Called every tick. Applies a temporary {@code WarbannerBuff} to all players in
	 * radius.
	 */
	@Override
	public void updateEntity() {
		int r = level * 2 + 2;
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(this.xCoord - r, this.yCoord - r, this.zCoord - r, this.xCoord + r, this.yCoord + r, this.zCoord + r);
		List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);
		//Loop through all players in radius
		for (EntityPlayer player : players) {
			PlayerData data = PlayerData.get(player);
			WarbannerBuff buff = data.getBuff(WarbannerBuff.class);
			//If the player doesn't have the buff, apply it
			if (buff == null) data.addBuff(new WarbannerBuff(0));
			//Otherwise, reset the starting tick of the buff to now so that it does not expire
			else buff.resetStartTick();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.level = nbt.getInteger("level");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("level", this.level);
	}
}
