package znick_.riskofrain2.util.helper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import znick_.riskofrain2.api.mc.Position;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.ArrowRainBorder;
import znick_.riskofrain2.block.RiskOfRain2Blocks;

public class StructureHelper {

	public static void arrowRainCircle(World world, int x, int y, int z, Block block) {
		if (world.getBlock(x, y, z).isReplaceable(world, x, y, z)) {
			world.setBlock(x, y, z, block);
		}
	}

}
