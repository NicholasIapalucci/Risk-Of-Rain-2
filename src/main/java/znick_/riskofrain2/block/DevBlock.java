package znick_.riskofrain2.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import znick_.riskofrain2.entity.elite.mobs.zombie.EntityBlazingZombie;
import znick_.riskofrain2.util.creativetabs.RiskOfRain2CreativeTabs;

public class DevBlock extends Block {

	public DevBlock() {
		super(Material.rock);
		this.setBlockName("dev_block");
		this.setCreativeTab(RiskOfRain2CreativeTabs.BLOCKS);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) world.spawnEntityInWorld(new EntityBlazingZombie(world, x, y, z));
		return true;
	}

}
