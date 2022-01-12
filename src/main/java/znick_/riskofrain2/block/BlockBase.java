package znick_.riskofrain2.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2CreativeTabs;

public class BlockBase extends Block {

	private boolean multiTextured = false;
	private String folder;
	private String name;
	
	public BlockBase(String name, String folder, float hardness, Material material, SoundType sound, float light, boolean opaque, boolean multiTextured) {
		super(material);

		this.name = name;
		this.opaque = opaque;
		this.multiTextured = multiTextured;
		this.folder = folder;
		
		this.setBlockName(name);
		this.setHardness(hardness);
		this.setStepSound(sound);
		this.setLightLevel(light);
		this.setBlockTextureName(RiskOfRain2.MODID + ":" + folder + "/" + name);
		this.setCreativeTab(RiskOfRain2CreativeTabs.BLOCKS);
	}
	
	public BlockBase(String name, String folder, float hardness, Material material, SoundType sound) {
		super(material);
		this.name = name;
		this.folder = folder;
		
		this.setBlockName(name);
		this.setHardness(hardness);
		this.setStepSound(sound);
		this.setBlockTextureName(RiskOfRain2.MODID + ":" + folder + "/" + name);
		this.setCreativeTab(RiskOfRain2CreativeTabs.BLOCKS);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return this.opaque;
	}
	
	public IIcon[] icons = new IIcon[6];
	
	@Override
    public void registerBlockIcons(IIconRegister reg) {
		if (this.multiTextured) {
			for (int i = 0; i < 6; i++) {
				switch(i) {
				case 0:
					this.icons[i] = reg.registerIcon(RiskOfRain2.MODID + ":" + this.folder + "/" + this.name + "_bottom");
					break;
				case 1:
					this.icons[i] = reg.registerIcon(RiskOfRain2.MODID + ":" + this.folder + "/" + this.name + "_top");
					break;
				default:
					this.icons[i] = reg.registerIcon(RiskOfRain2.MODID + ":" + this.folder + "/" + this.name + "_side");
				}
			}
		} else {
			for (int i = 0; i < 6; i++) this.icons[i] = reg.registerIcon(RiskOfRain2.MODID + ":" + this.folder + "/" + this.name);
		}
    }
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return this.icons[side];
	}
}
