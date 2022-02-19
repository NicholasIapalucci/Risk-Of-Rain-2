package znick_.riskofrain2.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import znick_.riskofrain2.api.mc.RiskOfRain2Config;
import znick_.riskofrain2.util.helper.StringHelper;

public class RiskOfRain2Biome extends BiomeGenBase {

	private final String name;
	
	protected RiskOfRain2Biome(String name) {
		super(RiskOfRain2Config.getBiomeID(name));
		this.name = name;
		this.setBiomeName(name);
	}
	
	public String getProperName() { 
		return StringHelper.format(this.name);
	}
}
