package znick_.riskofrain2.world;

import net.minecraft.world.WorldProvider;

public class RiskOfRain2Dimension extends WorldProvider {

	private final String name;
	
	protected RiskOfRain2Dimension(String name) {
		this.name = name;
	}
	
	@Override
	public String getDimensionName() {
		return this.name;
	}

}
