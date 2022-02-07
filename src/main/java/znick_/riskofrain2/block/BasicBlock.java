package znick_.riskofrain2.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2CreativeTabs;

public class BasicBlock extends Block {

	private final IIcon[] textures = new IIcon[6];
	private final boolean multiTextured;
	private final String folder;
	private final String name;
	
	public BasicBlock(String name, String folder, float hardness, Material material, SoundType sound, float light, boolean opaque, boolean multiTextured) {
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
	
	public BasicBlock(String name, String folder, float hardness, Material material, SoundType sound) {
		this(name, folder, hardness, material, sound, 0, true, false);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return this.opaque;
	}
	
	@Override
    public void registerBlockIcons(IIconRegister reg) {
		if (this.multiTextured) {
			for (int i = 0; i < 6; i++) {
				switch(i) {
				case 0:
					this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":" + this.folder + "/" + this.name + "_bottom");
					break;
				case 1:
					this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":" + this.folder + "/" + this.name + "_top");
					break;
				default:
					this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":" + this.folder + "/" + this.name + "_side");
				}
			}
		} else {
			for (int i = 0; i < 6; i++) {
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":" + this.folder + "/" + this.name);
			}
		}
    }
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return this.textures[side];
	}
}
