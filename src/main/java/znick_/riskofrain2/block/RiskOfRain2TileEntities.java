package znick_.riskofrain2.block;

import cpw.mods.fml.common.registry.GameRegistry;
import znick_.riskofrain2.block.itemgen.printer.TileEntity3DPrinter;
import znick_.riskofrain2.block.itemgen.smallchest.SmallChestTileEntity;

public class RiskOfRain2TileEntities {
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntity3DPrinter.class, "tile_entity_printer");
		GameRegistry.registerTileEntity(SmallChestTileEntity.class, "tile_entity_small_chest");
	}
}
