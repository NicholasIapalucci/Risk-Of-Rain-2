package znick_.riskofrain2;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.entity.item.EntityFireworkRocket;
import znick_.riskofrain2.api.mc.RiskOfRain2Config;
import znick_.riskofrain2.block.RiskOfRain2Blocks;
import znick_.riskofrain2.block.RiskOfRain2TileEntities;
import znick_.riskofrain2.client.gui.RiskOfRain2Guis;
import znick_.riskofrain2.client.keybind.RiskOfRain2KeyBinds;
import znick_.riskofrain2.client.render.RiskOfRain2Renders;
import znick_.riskofrain2.entity.RiskOfRain2Entities;
import znick_.riskofrain2.event.RiskOfRain2Events;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.net.RiskOfRain2Packets;
import znick_.riskofrain2.util.Registry;
import znick_.riskofrain2.util.creativetabs.RiskOfRain2CreativeTabs;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2Achievements;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2Commands;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2Recipes;

/**
 * The main class for the {@code Risk Of Rain 2} mod. Registers all items, blocks, tile entities, etc.
 * 
 * @author zNick_
 */
@Mod(
	modid = RiskOfRain2.MODID,
	name = "Risk Of Rain 2", 
	version = RiskOfRain2.VERSION,
	dependencies = "required-after:difficultlife"
)
public class RiskOfRain2 {
	
	@Instance("ror2")
	public static RiskOfRain2 instance;
	public static final String MODID = "ror2";
	public static final String VERSION = "1.0";
	public static final boolean DEBUG = true;
	
	/**
	 * Called during pre-initialization. Registers creative tabs, items, blocks, entities, renderers, tile entities,
	 * GUIs, events, keybindings, achievements, recipes, and packets.
	 * 
	 * @param event The {@code PreInitiazliationEvent}.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		RiskOfRain2Config.initConfig(event);
		RiskOfRain2TileEntities.registerTileEntities();
		RiskOfRain2Packets.registerPackets();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		RiskOfRain2Items.registerItems();
		RiskOfRain2CreativeTabs.registerCreativeTabs();
		RiskOfRain2Blocks.registerBlocks();
		RiskOfRain2Entities.registerEntities();
		RiskOfRain2Renders.registerRenders();
		RiskOfRain2Guis.registerGuis();
		RiskOfRain2Events.registerEvents();
		RiskOfRain2KeyBinds.registerKeyBinds();
		RiskOfRain2Achievements.registerAchievements();
		RiskOfRain2Recipes.registerRecipes();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@EventHandler
	public void loadServer(FMLServerStartingEvent event) {
		RiskOfRain2Commands.registerCommands(event);
	}
	
	//TODO: Add ------------------------------------------
	//TODO: Fix -------------------------------------------
	//TODO: Update ----------------------------------------

}