package znick_.riskofrain2.block.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;
import znick_.riskofrain2.block.itemblock.IBlockItem;
import znick_.riskofrain2.client.gui.GuiHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.itemblock.ItemBlockChest;
import znick_.riskofrain2.tile.RoR2TileEntityChest;
import znick_.riskofrain2.util.helper.MinecraftHelper;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2CreativeTabs;

public class RiskOfRain2Chest extends BlockContainer implements IBlockItem {

	private IIcon[] textures = new IIcon[6];
	
	//TODO: Add chest generation
	public RiskOfRain2Chest() {
		super(Material.rock);
		this.setBlockName("risk_chest");
		this.setBlockTextureName(RiskOfRain2.MODID + ":chest");
		this.setCreativeTab(RiskOfRain2CreativeTabs.MISC);
		this.setBlockUnbreakable();
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
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		RoR2TileEntityChest te = (RoR2TileEntityChest) world.getTileEntity(x, y, z);
		for (int i = 0; i < te.getSizeInventory(); i++) if (te.getStackInSlot(i) != null) dropBlockAsItem(world, x, y, z, te.getStackInSlot(i));
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
//		PlayerData properties = PlayerData.get(player);
//		if (!world.isRemote && (properties.getMoney() >= 25 || player.capabilities.isCreativeMode)) {	
//			player.openGui(RiskOfRain2.instance, GuiHandler.CHEST_ID, world, x, y, z);
//			if (!player.capabilities.isCreativeMode) properties.consumeMoney(25);
//		}
		return true;
	}
	
	public static RiskOfRain2Item rollSmall() {
		ItemRarity rarity;
		
		double rand = Math.random() * 100;
		if (rand < 0.99) rarity = ItemRarity.RED;
		else if (rand < 19.8) rarity = ItemRarity.GREEN;
		else rarity = ItemRarity.WHITE;
		
		List<RiskOfRain2Item> items = new ArrayList<RiskOfRain2Item>();
		for (RiskOfRain2Item item : RiskOfRain2Items.ITEM_SET) if (item.getRarity() == rarity) items.add(item);
		return items.get(new Random().nextInt(items.size()));
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockChest.class;
	}

}
