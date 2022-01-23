package znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import znick_.riskofrain2.RiskOfRain2;

public class ArrowRainBorder extends Block {

	private String localTexture = "";
	
	public ArrowRainBorder() {
		super(Material.rock);
		this.setBlockUnbreakable();
		this.setBlockBounds(0, 0, 0, 1, 0.0625f, 1);
		this.localTexture = "ring";
		this.setBlockName(this.localTexture);
	}
	
	public IIcon[] icons = new IIcon[6];

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			switch(i) {
			case 0:
				this.icons[i] = reg.registerIcon(RiskOfRain2.MODID + ":character/huntress/arrowrain/" + this.localTexture);
				break;
			case 1:
				this.icons[i] = reg.registerIcon(RiskOfRain2.MODID + ":character/huntress/arrowrain/" + this.localTexture);
				break;
			default:
				this.icons[i] = reg.registerIcon(RiskOfRain2.MODID + ":character/huntress/arrowrain/side");
			}
		}
		
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.icons[side];
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return true;
    }
	
	@Override
    public int getRenderBlockPass() {
        return 0;
    }

	@Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
