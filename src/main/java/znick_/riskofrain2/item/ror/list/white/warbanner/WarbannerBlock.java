package znick_.riskofrain2.item.ror.list.white.warbanner;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2CreativeTabs;

public class WarbannerBlock extends Block implements ITileEntityProvider {

	public WarbannerBlock() {
		super(Material.wood);
		this.setBlockName("warbanner_block");
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(RiskOfRain2CreativeTabs.BLOCKS);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new WarbannerTileEntity();
	}

}
