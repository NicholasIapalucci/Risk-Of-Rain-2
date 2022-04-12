package znick_.riskofrain2.block.itemgen.smallchest;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.enums.Face;
import znick_.riskofrain2.block.itemblock.IBlockItem;
import znick_.riskofrain2.block.util.BlockFace;
import znick_.riskofrain2.block.util.FacingBlock;
import znick_.riskofrain2.util.creativetabs.RiskOfRain2CreativeTabs;

public class SmallChestBlock extends Block implements IBlockItem, ITileEntityProvider, FacingBlock {

	private final IIcon[] closedTextures = new IIcon[6];
	
	public SmallChestBlock() {
		super(Material.rock);
		this.setBlockName("small_chest");
		this.setBlockTextureName(RiskOfRain2Mod.MODID + ":chest");
		this.setCreativeTab(RiskOfRain2CreativeTabs.BLOCKS);
		this.setBlockUnbreakable();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
		this.setMetadataUponPlacing(world, x, y, z, placer);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new SmallChestTileEntity();
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			switch(i) {
			case 0:
				this.closedTextures[i] = reg.registerIcon(RiskOfRain2Mod.MODID + ":chest/bottom");
				break;
			case 1:
				this.closedTextures[i] = reg.registerIcon(RiskOfRain2Mod.MODID + ":chest/top");
				break;
			case 2:
				this.closedTextures[i] = reg.registerIcon(RiskOfRain2Mod.MODID + ":chest/back");
				break;
			case 3:
				this.closedTextures[i] = reg.registerIcon(RiskOfRain2Mod.MODID + ":chest/front");
				break;
			default:
				this.closedTextures[i] = reg.registerIcon(RiskOfRain2Mod.MODID + ":chest/side");
			}
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.closedTextures[side];
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		SmallChestTileEntity chest = (SmallChestTileEntity) world.getTileEntity(x, y, z);
		if (chest.isOpened()) return false;
		return chest.open(player);
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockChest.class;
	}

	@Override
	public Face getFacingDirection() {
		return null;
	}

	@Override
	public String getSideTexture(BlockFace face) {
		return null;
	}

}
