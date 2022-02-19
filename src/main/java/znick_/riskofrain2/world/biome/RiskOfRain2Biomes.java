package znick_.riskofrain2.world.biome;

import java.util.HashSet;
import java.util.Set;

import znick_.riskofrain2.api.mc.RiskOfRain2Config;
import znick_.riskofrain2.world.biome.biomelist.TitanicPlainsBiome;

public class RiskOfRain2Biomes {

	private static final Set<RiskOfRain2Biome> BIOMES = new HashSet<>();
	private static int nextID = 200;
	
	public static final RiskOfRain2Biome TITANIC_PLAINS = new TitanicPlainsBiome();
	
	public static RiskOfRain2Biome[] getBiomes() {
		return BIOMES.toArray(new RiskOfRain2Biome[0]);	
	}
}
