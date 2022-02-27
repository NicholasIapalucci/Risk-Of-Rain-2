package znick_.riskofrain2.api.mc;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class RiskOfRain2Config {

	private static Configuration config;
	private static int nextID = 200;
	
	private static final Map<String, Integer> BIOME_IDS = new HashMap<>();
	private static boolean isMenuScreenEnabled = true;
	
	public static void initConfig(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		BIOME_IDS.put("titanic_plains", config.get("Biome IDs", "Titanic Plains", nextID++).getInt());
		BIOME_IDS.put("distant_roost", config.get("Biome IDs", "Distant Roost", nextID++).getInt());
		BIOME_IDS.put("abandoned_aqueduct", config.get("Biome IDs", "Abandoned Aqueduct", nextID++).getInt());
		BIOME_IDS.put("wetland_aspect", config.get("Biome IDs", "Wetland Aspect", nextID++).getInt());
		BIOME_IDS.put("rallypoint_delta", config.get("Biome IDs", "Rallypoint Delta", nextID++).getInt());
		BIOME_IDS.put("scorched_acres", config.get("Biome IDs", "Scorched Acres", nextID++).getInt());
		
		isMenuScreenEnabled = config.getBoolean("Enable Menu Screen", "Miscellaneous", true, "");
		
		if (config.hasChanged()) config.save();
	}
	
	public static int getBiomeID(String biomeName) {
		return BIOME_IDS.get(biomeName);
	}
	
	public static boolean isMenuScreenEnabled() {
		return isMenuScreenEnabled;
	}
}
