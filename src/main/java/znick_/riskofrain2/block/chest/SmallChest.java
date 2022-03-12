package znick_.riskofrain2.block.chest;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.block.itemblock.IBlockItem;
import znick_.riskofrain2.event.rorevents.ObjectInteractionEvent;
import znick_.riskofrain2.item.itemblock.ItemBlockChest;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2CreativeTabs;

public class SmallChest extends Block implements IBlockItem, ItemGenerator {

	private final IIcon[] textures = new IIcon[6];
	
	public SmallChest() {
		super(Material.rock);
		this.setBlockName("risk_chest");
		this.setBlockTextureName(RiskOfRain2.MODID + ":chest");
		this.setCreativeTab(RiskOfRain2CreativeTabs.MISC);
		this.setBlockUnbreakable();
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			switch(i) {
			case 0:
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":chest/bottom");
				break;
			case 1:
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":chest/top");
				break;
			case 2:
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":chest/back");
				break;
			case 3:
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":chest/front");
				break;
			default:
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":chest/side");
			}
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.textures[side];
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		TileEntityRoRChest tile = (TileEntityRoRChest) world.getTileEntity(x, y, z);
		ObjectInteractionEvent event = new ObjectInteractionEvent(player, tile) ;
		if (!MinecraftForge.EVENT_BUS.post(event)) return false;	
		return true;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockChest.class;
	}

	@Override
	public Map<ItemRarity, Double> getRarityWeights() {
		return null;
	}

}
