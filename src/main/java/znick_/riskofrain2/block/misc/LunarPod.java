package znick_.riskofrain2.block.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.block.itemblock.IBlockItem;
import znick_.riskofrain2.client.gui.GuiHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.itemblock.ItemBlockLunarPod;
import znick_.riskofrain2.tile.RoR2TileEntityChest;
import znick_.riskofrain2.util.helper.InventoryHelper;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2CreativeTabs;

public class LunarPod extends BlockContainer implements IBlockItem {

	private IIcon[] textures = new IIcon[6];
	
	//TODO: Add lunar pod generation
	public LunarPod() {
		super(Material.cloth);
		this.setStepSound(soundTypeSnow);
		this.setBlockUnbreakable();
		this.setBlockTextureName(RiskOfRain2.MODID + ":lunarPod"); //TODO: Add lunar pod textures
		this.setCreativeTab(RiskOfRain2CreativeTabs.MISC);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new RoR2TileEntityChest();
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			switch(i) {
			case 0:
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":lunarPod/bottom");
				break;
			case 1:
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":lunarPod/top");
				break;
			case 2:
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":lunarPod/back");
				break;
			case 3:
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":lunarPod/front");
				break;
			default:
				this.textures[i] = reg.registerIcon(RiskOfRain2.MODID + ":lunarPod/side");
			}
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.textures[side];
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		RoR2TileEntityChest te = (RoR2TileEntityChest) world.getTileEntity(x, y, z);

		for (int i = 0; i < te.getSizeInventory(); i++) {

			if (te.getStackInSlot(i) != null) {
				dropBlockAsItem(world, x, y, z, te.getStackInSlot(i));
			}
		}

		super.breakBlock(world, x, y, z, block, meta);
	}

	//TODO: Add lunar coins
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		PlayerData properties = PlayerData.get(player);
//		if (!world.isRemote && properties.getLunarCoins() >= 1) {	
//			player.openGui(RiskOfRain2.instance, GuiHandler.CHEST_ID, world, x, y, z);
//			if (!player.capabilities.isCreativeMode) properties.consumeLunarCoins(1);
//		}
		
		return true;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockLunarPod.class;
	}

}
