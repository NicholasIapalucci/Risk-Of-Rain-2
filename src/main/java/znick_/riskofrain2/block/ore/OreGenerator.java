package znick_.riskofrain2.block.ore;

import java.lang.reflect.Field;
import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import znick_.riskofrain2.api.mc.Position;
import znick_.riskofrain2.block.RiskOfRain2Blocks;

public class OreGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case -1:
			this.generateNether(world, random, chunkX, chunkZ);
			break;
		case 0:
			this.generateOverworld(world, random, chunkX, chunkZ);
			break;
		case 1:
			this.generateEnd(world, random, chunkX, chunkZ);
			break;
		}
	}

	public void generateNether(World world, Random rand, int x, int z) {
		
	}
	
	public void generateOverworld(World world, Random rand, int x, int z) {
		try {
			Field[] fields = RiskOfRain2Blocks.class.getDeclaredFields();
			for (Field field : fields) {
				if (field.get(null) instanceof OreBlock) {
					OreBlock block = (OreBlock) field.get(null);
					this.generateOre(block, world, rand, x, z);
				}
			}
		} 
		catch(Exception e) {throw new RuntimeException(e.getCause() + ": " + e.getMessage());}
	}

	public void generateEnd(World world, Random rand, int x, int z) {
		
	}

	public void generateOre(OreBlock block, World world, Random random, int chunkX, int chunkZ) {
		int veinSize = block.getOreData().getMaxVeinSize() == 1 ? 1 : block.getOreData().getMinVeinSize() + random.nextInt(block.getOreData().getMaxVeinSize() - block.getOreData().getMinVeinSize());
		int heightRange = block.getOreData().getUpperY() - block.getOreData().getLowerY();
		WorldGenMinable gen = new WorldGenMinable(block, veinSize, block.getOreData().getSpawnBlock());
		
		for (int i = 0; i < block.getOreData().getSpawnWeight() * 50; i++) {
			int xRand = chunkX * 16 + random.nextInt(16);
			int yRand = random.nextInt(heightRange) + block.getOreData().getLowerY();
			int zRand = chunkZ * 16 + random.nextInt(16);
			gen.generate(world, random, xRand, yRand, zRand);
		}
	}

}
