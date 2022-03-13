package znick_.riskofrain2.block;

import java.lang.reflect.Field;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import znick_.riskofrain2.block.itemblock.BlockItem;
import znick_.riskofrain2.block.itemblock.IBlockItem;
import znick_.riskofrain2.block.itemgen.lunarpod.LunarPod;
import znick_.riskofrain2.block.itemgen.printer.Printer3D;
import znick_.riskofrain2.block.itemgen.smallchest.SmallChestBlock;
import znick_.riskofrain2.block.ore.OreBlock;
import znick_.riskofrain2.block.ore.OreData;
import znick_.riskofrain2.block.ore.OreGenerator;
import znick_.riskofrain2.item.ror.list.white.warbanner.WarbannerBlock;

public class RiskOfRain2Blocks {
	
	//Huntress
	public static final Block HUNTRITE_ORE = new OreBlock("huntrite_ore", new OreData().setUpperY(30).setSpawnWeight(0.1f).setMaxVeinSize(1));
	public static final Block TRESSIUM_ORE = new OreBlock("tressium_ore", new OreData().setUpperY(30).setSpawnWeight(0.1f).setMaxVeinSize(1));
	public static final Block HUNTRITE_BLOCK = new BlockItem("huntrite_block", "cosmetic", 5, Material.iron, Block.soundTypeMetal);
	public static final Block TRESSIUM_BLOCK = new BlockItem("tressium_block", "cosmetic", 5, Material.iron, Block.soundTypeMetal);
	
	//Items
	public static final Block WARBANNER_BLOCK = new WarbannerBlock();
	
	//Misc
	public static final Block PRINTER_3D = new Printer3D();
	public static final Block CHEST = new SmallChestBlock();
	public static final Block LUNAR_POD = new LunarPod();
	
	/**
	 * Registers all blocks into the game. All blocks in the {@code RiskOfRain2Blocks} class will be registered.
	 * Blocks that are instanceof {@link znick_.riskofrain2.block.itemblock.IBlockItem IBlockItem} will be registered
	 * with their respective {@code ItemBlock} class.
	 */
	public static final void registerBlocks() {
		// Loop through all fields in the class
		for (Field field : RiskOfRain2Blocks.class.getDeclaredFields()) {
			try {
				// Register the block as an ItemBlock if necessary
				if (field.get(null) instanceof IBlockItem) {
					IBlockItem blockItem = (IBlockItem) field.get(null);
					Block block = (Block) field.get(null);
					GameRegistry.registerBlock(block, blockItem.getItemBlockClass(), block.getUnlocalizedName());
				}
				
				// Otherwise, register it normally
				else if (field.get(null) instanceof Block) {
					Block block = (Block) field.get(null);
					GameRegistry.registerBlock(block, block.getUnlocalizedName());
				}
			} 
			
			//Throw an exception if there is an exception thrown
			catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		// Register ore generation
		GameRegistry.registerWorldGenerator(new OreGenerator(), 0);
	}
}
