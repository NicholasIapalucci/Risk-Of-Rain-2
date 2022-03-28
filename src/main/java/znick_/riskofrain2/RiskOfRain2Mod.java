package znick_.riskofrain2;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
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
import znick_.riskofrain2.util.achievement.RiskOfRain2Achievements;
import znick_.riskofrain2.util.creativetabs.RiskOfRain2CreativeTabs;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2Commands;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2Recipes;

/**
 * The main class for the {@code Risk Of Rain 2} mod. Registers all items, blocks, tile entities, etc.
 * 
 * @author zNick_
 */
@Mod(
	modid = RiskOfRain2Mod.MODID,
	name = RiskOfRain2Mod.MOD_NAME, 
	version = RiskOfRain2Mod.VERSION
)
public class RiskOfRain2Mod {
	
	@Instance("ror2")
	public static RiskOfRain2Mod instance;
	
	/**The mod ID for the Risk of Rain 2 mod. This mod ID must be unique for Minecraft, so any other mods with
	 * the mod ID will not be compatable with this mod. This is currently set to "ror2", but it is
	 * recommended to use this variable instead of referencing it literally in case it changes.*/
	public static final String MODID = "ror2";
	
	/**
	 * The version for the Risk of Rain 2 mod. 0.001x for pre-alpha, 0.01x for alpha, 0.1x for beta, and
	 * 1.x for release versions. 
	 */
	public static final String VERSION = "0.001";
	
	/**
	 * The formatted and official name for the mod, written as "Risk Of Rain 2". This should not be used
	 * in any back-end development, but only for front-end display.
	 */
	public static final String MOD_NAME = "Risk Of Rain 2";
	
	/**
	 * Whether or not debug is currently enabled. Used to print debug statements if in a development
	 * environment. 
	 */
	public static final boolean DEBUG = true;
	
	/**
	 * Called during pre-initialization. Registers creative tabs, items, blocks, entities, renderers, tile entities,
	 * GUIs, events, keybindings, achievements, and packets.
	 * 
	 * @param event The {@code PreInitiazliationEvent}.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		RiskOfRain2Config.initConfig(event);
		RiskOfRain2TileEntities.registerTileEntities();
		RiskOfRain2Packets.registerPackets();
		RiskOfRain2Items.registerItems();
		RiskOfRain2CreativeTabs.registerCreativeTabs();
		RiskOfRain2Blocks.registerBlocks();
		RiskOfRain2Entities.registerEntities();
		RiskOfRain2Renders.registerRenders();
		RiskOfRain2Guis.registerGuis();
		RiskOfRain2Events.registerEvents();
		RiskOfRain2KeyBinds.registerKeyBinds();
		RiskOfRain2Achievements.registerAchievements();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
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