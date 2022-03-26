package znick_.riskofrain2.util.file;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2Mod;

/**
 * Class of static methods and fields used for managing {@code ResourceLocations}
 * for the Risk Of Rain 2 mod.
 * 
 * @author zNick_
 */
public class RiskOfRain2Resources {

	private static final Map<String, ResourceLocation> CACHED_RESOURCE_LOCATIONS = new HashMap<>();
	
	public static final String BUFFS = RiskOfRain2Mod.MODID + ":textures/gui/buffs/";
	public static final String TEXTURES = RiskOfRain2Mod.MODID + ":textures/";
	public static final String GUI = RiskOfRain2Mod.MODID + ":textures/gui/";
	public static final String ITEMS = RiskOfRain2Mod.MODID + ":textures/items/";
	
	/**
	 * Returns a {@code ResourceLocation} with the specified resource location. Equivalent to
	 * {@code new ResourceLocation(resource}. Using this method caches the resource local for
	 * later use, so that using it multiple times with the same resource location will return
	 * the cached object isntead of creating a new {@code ResourceLocation} object. Thus, this
	 * method is preferable to {@code new ResourceLocation(resource)} because it saves memory
	 * by not creating unnecessary objects. 
	 * 
	 * @param resource The location of the resource.
	 * 
	 * @return A {@code ResourceLocation} object with the specified location.
	 */
	public static ResourceLocation get(String resource) {
		if (!resource.endsWith(".png")) resource += ".png";
		if (!CACHED_RESOURCE_LOCATIONS.containsKey(resource)) CACHED_RESOURCE_LOCATIONS.put(resource, new ResourceLocation(resource));
		return CACHED_RESOURCE_LOCATIONS.get(resource);
	}
}
