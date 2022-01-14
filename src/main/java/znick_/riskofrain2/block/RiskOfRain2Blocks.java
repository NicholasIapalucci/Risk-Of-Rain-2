package znick_.riskofrain2.block;

import java.lang.reflect.Field;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import znick_.riskofrain2.api.ror.character.huntress.ArrowRainBorder;
import znick_.riskofrain2.api.ror.items.list.white.warbanner.WarbannerBlock;
import znick_.riskofrain2.block.misc.LunarPod;
import znick_.riskofrain2.block.misc.RiskOfRain2Chest;
import znick_.riskofrain2.block.ore.OreBlock;
import znick_.riskofrain2.block.ore.OreGenerator;
import znick_.riskofrain2.block.printer.Printer3D;
import znick_.riskofrain2.util.misc.OreData;

public class RiskOfRain2Blocks {
	
	public static final Block riskChest = new RiskOfRain2Chest();
	public static final Block lunarPod = new LunarPod();
	
	public static final Block huntriteOre = new OreBlock("huntrite_ore", new OreData().setUpperY(30).setSpawnWeight(0.1f).setMaxVeinSize(1));
	public static final Block tressiumOre = new OreBlock("tressium_ore", new OreData().setUpperY(30).setSpawnWeight(0.1f).setMaxVeinSize(1));
	public static final Block huntriteBlock = new BlockItem("huntrite_block", "cosmetic", 5, Material.iron, Block.soundTypeMetal);
	public static final Block tressiumBlock = new BlockItem("tressium_block", "cosmetic", 5, Material.iron, Block.soundTypeMetal);
	
	public static final Block ring = new ArrowRainBorder(true, true, ArrowRainBorder.BorderType.RING);
	
	public static final Block PRINTER_3D = new Printer3D();
	
	public static final Block WARBANNER_BLOCK = new WarbannerBlock();
	
	public static final void registerBlocks() {
		for (Field field : RiskOfRain2Blocks.class.getDeclaredFields()) {
			try {
				if (field.get(null) instanceof IBlockItem) {
					IBlockItem blockItem = (IBlockItem) field.get(null);
					Block block = (Block) field.get(null);
					GameRegistry.registerBlock(block, blockItem.getItemBlockClass(), block.getUnlocalizedName());
				}
				else if (field.get(null) instanceof Block) {
					Block block = (Block) field.get(null);
					GameRegistry.registerBlock(block, block.getUnlocalizedName());
				}
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		GameRegistry.registerWorldGenerator(new OreGenerator(), 0);
	}
}
