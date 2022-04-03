package znick_.riskofrain2.block.itemgen.cloakedchest;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.block.itemblock.ItemBlockGray;
import znick_.riskofrain2.block.itemgen.smallchest.SmallChestBlock;

/**
 * Class for creating the cloaked chest block.
 * 
 * @author zNick_
 */
public class CloakedChest extends SmallChestBlock {
	
	/**Creates a new cloaked chest.*/
	public CloakedChest() {
		super();
		this.setBlockName("cloaked_chest");
		this.setBlockTextureName(RiskOfRain2Mod.MODID + ":chest/cloaked/cloaked_chest");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(this.getTextureName());
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.blockIcon;
	}
	 
	@Override
	public boolean isOpaqueCube() {
        return false;
    }
	
	@Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockGray.class;
	}
}
