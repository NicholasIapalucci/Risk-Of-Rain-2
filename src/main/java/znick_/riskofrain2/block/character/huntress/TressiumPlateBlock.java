package znick_.riskofrain2.block.character.huntress;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.util.creativetabs.RiskOfRain2CreativeTabs;

public class TressiumPlateBlock extends Block {

	/**The percentage of a block equal to 2 pixels. Used for setting the block bounds.*/
	private static final float P2 = 1f/8f;
	private final IIcon[] textures = new IIcon[6];
	
	public TressiumPlateBlock() {
		super(Material.rock);
		this.setBlockName("tressium_plate_block");
		this.setCreativeTab(RiskOfRain2CreativeTabs.BLOCKS);
		this.setBlockBounds(P2, 0, P2, 1 - P2, P2, 1 - P2);
		this.useNeighborBrightness = true;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
    public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			if (i < 2) this.textures[i] = reg.registerIcon(RiskOfRain2Mod.MODID + ":character/huntress/tressium_plate");
			else this.textures[i] = reg.registerIcon(RiskOfRain2Mod.MODID + ":character/huntress/tressium_plate_side");
		}
    }
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return this.textures[side];
	}
}
