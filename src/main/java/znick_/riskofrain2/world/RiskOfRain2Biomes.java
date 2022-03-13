package znick_.riskofrain2.world;

import java.util.HashSet;
import java.util.Set;

public class RiskOfRain2Biomes {

	private static final Set<RiskOfRain2Biome> BIOMES = new HashSet<>();
	private static int nextID = 200;
		
	public static RiskOfRain2Biome[] getBiomes() {
		return BIOMES.toArray(new RiskOfRain2Biome[0]);	
	}
}
