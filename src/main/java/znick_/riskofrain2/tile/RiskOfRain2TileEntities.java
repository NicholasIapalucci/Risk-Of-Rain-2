package znick_.riskofrain2.tile;

import java.lang.reflect.Field;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import znick_.riskofrain2.block.RiskOfRain2Blocks;
import znick_.riskofrain2.block.printer.TileEntity3DPrinter;

public class RiskOfRain2TileEntities {
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(RoR2TileEntityChest.class, "tileEntityChest");
		GameRegistry.registerTileEntity(TileEntity3DPrinter.class, "tileEntityPrinter");
	}
}
