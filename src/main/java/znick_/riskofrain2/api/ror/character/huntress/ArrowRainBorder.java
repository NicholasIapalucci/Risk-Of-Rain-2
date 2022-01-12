package znick_.riskofrain2.api.ror.character.huntress;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import znick_.riskofrain2.RiskOfRain2;

public class ArrowRainBorder extends Block {

	private boolean xPos;
	private boolean zPos;
	private String localTexture = "";
	private BorderType borderType;
	
	public ArrowRainBorder(boolean xPos, boolean zPos, BorderType borderType) {
		super(Material.rock);
		this.setBlockUnbreakable();
		this.setBlockBounds(0, 0, 0, 1, 0.0625f, 1);
		
		this.opaque = false;
		this.useNeighborBrightness = true;
		this.xPos = xPos;
		this.zPos = zPos;
		this.borderType = borderType;
		
		if (xPos) {
			if (zPos) {
				switch(borderType) {
				case CORNER:
					this.localTexture = "corner_xPos_zPos";
					break;
				case EDGE:
					this.localTexture = "edge_xPos";
					break;
				case CORNERBIT:
					this.localTexture = "cornerbit_xPos_zPos";
					break;
				case RING:
					this.localTexture = "ring";
					break;
				}
			} else {
				switch(borderType) {
				case CORNER:
					this.localTexture = "corner_xPos_zNeg";
					break;
				case EDGE:
					this.localTexture = "edge_xNeg";
					break;
				case CORNERBIT:
					this.localTexture = "cornerbit_xPos_zNeg";
					break;
				case RING:
					this.localTexture = "ring";
					break;
				}
			}
		} else {
			if (zPos) {
				switch(borderType) {
				case CORNER:
					this.localTexture = "corner_xNeg_zPos";
					break;
				case EDGE:
					this.localTexture = "edge_zPos";
					break;
				case CORNERBIT:
					this.localTexture = "cornerbit_xNeg_zPos";
					break;
				case RING:
					this.localTexture = "ring";
					break;
				}
			} else {
				switch(borderType) {
				case CORNER:
					this.localTexture = "corner_xNeg_zNeg";
					break;
				case EDGE:
					this.localTexture = "edge_zNeg";
					break;
				case CORNERBIT:
					this.localTexture = "cornerbit_xNeg_zNeg";
					break;
				case RING:
					this.localTexture = "ring";
					break;
				}
			}
		}
		
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
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side){
		switch(this.borderType) {
		case CORNER:
			switch(side) {
			case 0:
				return true;
			case 1:
				return true;
			case 2:
				return !this.zPos;
			case 3:
				return this.zPos;
			case 4:
				return !this.xPos;
			case 5:
				return this.xPos;
			}
		case EDGE:
			switch(side) {
			case 0:
				return true;
			case 1:
				return true;
			case 2:
				return (!this.xPos && !this.zPos);
			case 3:
				return (!this.xPos && this.zPos);
			case 4:
				return (this.xPos && !this.zPos);
			case 5:
				return (this.xPos && this.zPos);
			}
		case CORNERBIT:
			switch(side) {
			case 0:
				return true;
			case 1:
				return true;
			default:
				return false;
			}
		case RING:
			return true;
		}
		
		return false;
    }
	
	@Override
    public int getRenderBlockPass() {
        return 0;
    }

	@Override
    public boolean renderAsNormalBlock() {
        return false;
    }

	public enum BorderType {
		CORNER,
		EDGE,
		CORNERBIT,
		RING;
	}
}
