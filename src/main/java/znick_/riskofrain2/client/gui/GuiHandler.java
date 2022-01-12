package znick_.riskofrain2.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import znick_.riskofrain2.block.misc.LunarPod;
import znick_.riskofrain2.block.misc.RiskOfRain2Chest;
import znick_.riskofrain2.client.gui.block.GuiChest;
import znick_.riskofrain2.tile.RoR2ContainerChest;
import znick_.riskofrain2.tile.RoR2TileEntityChest;

public class GuiHandler implements IGuiHandler {

	public final static int CHEST_ID = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == CHEST_ID) {
			Block block = world.getBlock(x, y, z);
			if (block instanceof RiskOfRain2Chest) {
				RiskOfRain2Chest chest = (RiskOfRain2Chest) block;
				return new RoR2ContainerChest(player.inventory, (RoR2TileEntityChest) world.getTileEntity(x, y, z));
			}
			if (block instanceof LunarPod) {
				LunarPod lunarPod = (LunarPod) block;
				return new RoR2ContainerChest(player.inventory, (RoR2TileEntityChest) world.getTileEntity(x, y, z));
			}
		}
		
		throw new IllegalArgumentException("There is no GUI with the ID " + ID);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == CHEST_ID) {
			Block block = world.getBlock(x, y, z);
			if (block instanceof RiskOfRain2Chest) {
				RiskOfRain2Chest chest = (RiskOfRain2Chest) block;
				return new GuiChest(world, x, y, z, player.inventory, (RoR2TileEntityChest) world.getTileEntity(x, y, z));
			}
			if (block instanceof LunarPod) {
				LunarPod lunarPod = (LunarPod) block;
				return new GuiChest(world, x, y, z, player.inventory, (RoR2TileEntityChest) world.getTileEntity(x, y, z));
			}
		}
		
		throw new IllegalArgumentException("There is no GUI with the ID " + ID);
	}
}
