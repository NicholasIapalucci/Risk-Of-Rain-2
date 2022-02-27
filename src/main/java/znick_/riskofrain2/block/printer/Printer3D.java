package znick_.riskofrain2.block.printer;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import znick_.riskofrain2.api.ror.items.ItemGenerator;
import znick_.riskofrain2.event.rorevents.ObjectInteractionEvent;
import znick_.riskofrain2.event.rorevents.ObjectInteractionEvent.ObjectType;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2CreativeTabs;

/**
 * Class for creating the {@link znick_.riskofrain2.block.RiskOfRain2Blocks#PRINTER_3D 3DPrinter} block.
 * 
 * @author zNick_
 */
public class Printer3D extends Block implements ITileEntityProvider {
	
	/**
	 * Creates a new 3D Printer block.
	 */
	public Printer3D() {
		super(Material.rock);
		this.setBlockName("printer");
		this.setBlockUnbreakable();
		this.opaque = false;
		this.setCreativeTab(RiskOfRain2CreativeTabs.BLOCKS);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntity3DPrinter(ItemGenerator.generateSmallChestItem()); //TODO: Add Boss printer
	}
	
	/**
	 * Called when the printer is right clicked. If it 
	 * {@link znick_.riskofrain2.block.printer.TileEntity3DPrinter#print(EntityPlayer) prints}, the printer sound
	 * will play.
	 * 
	 * @param world The world the block is in
	 * @param x The x-coordinate of the block
	 * @param y The y-coordinate of the block
	 * @param z The z-coordinate of the block
	 * @param player The player who clicked the block
	 * @param side The side of the block that was clicked
	 * @param hitX Where in the x-direction the block was clicked
	 * @param hitY Where in the y-direction the block was clicked
	 * @param hitZ Where in the z-direction the block was clicked
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ObjectInteractionEvent event = new ObjectInteractionEvent(player, ObjectType.PRINTER_3D);
		if (!MinecraftForge.EVENT_BUS.post(event)) return false;
		TileEntity3DPrinter tile = (TileEntity3DPrinter) world.getTileEntity(x, y, z);
		if (!tile.isOnCooldown() && tile.print(player)) {
			player.playSound("ror2:printer", 1, 1);
			return true;
		}
		return false;
	}

	@Override
	public boolean isBlockNormalCube() {
		return false;
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
        return false;
    }

	@Override
	public int getRenderType() {
		return Render3DPrinter.RENDER_ID;
	}
	
}
