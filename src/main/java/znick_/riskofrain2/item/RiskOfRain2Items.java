package znick_.riskofrain2.item;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.api.ror.character.PlayableCharacter;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.list.ScrapItem;
import znick_.riskofrain2.api.ror.items.list.boss.pearl.PearlItem;
import znick_.riskofrain2.api.ror.items.list.boss.titanicknurl.TitanicKnurlItem;
import znick_.riskofrain2.api.ror.items.list.equipment.ForeignFruitItem;
import znick_.riskofrain2.api.ror.items.list.equipment.jadeelephant.JadeElephantItem;
import znick_.riskofrain2.api.ror.items.list.equipment.ocularhud.OcularHudItem;
import znick_.riskofrain2.api.ror.items.list.equipment.supermassiveleech.SuperMassiveLeechItem;
import znick_.riskofrain2.api.ror.items.list.green.Chronobauble;
import znick_.riskofrain2.api.ror.items.list.green.FuelCell;
import znick_.riskofrain2.api.ror.items.list.green.HarvestersScythe;
import znick_.riskofrain2.api.ror.items.list.green.HopooFeather;
import znick_.riskofrain2.api.ror.items.list.green.LeechingSeed;
import znick_.riskofrain2.api.ror.items.list.green.OldWarStealthkit;
import znick_.riskofrain2.api.ror.items.list.green.WaxQuail;
import znick_.riskofrain2.api.ror.items.list.green.WillOTheWisp;
import znick_.riskofrain2.api.ror.items.list.green.infusion.InfusionItem;
import znick_.riskofrain2.api.ror.items.list.lunar.BeadsOfFealty;
import znick_.riskofrain2.api.ror.items.list.lunar.Purity;
import znick_.riskofrain2.api.ror.items.list.lunar.ShapedGlass;
import znick_.riskofrain2.api.ror.items.list.red.AlienHead;
import znick_.riskofrain2.api.ror.items.list.red.BrilliantBehemothItem;
import znick_.riskofrain2.api.ror.items.list.red.HardlightAfterburner;
import znick_.riskofrain2.api.ror.items.list.red.HeadSet;
import znick_.riskofrain2.api.ror.items.list.red.RejuvinationRack;
import znick_.riskofrain2.api.ror.items.list.red.diosbestfriend.DiosBestFriend;
import znick_.riskofrain2.api.ror.items.list.red.fiftysevenleafclover.FiftySevenLeafClover;
import znick_.riskofrain2.api.ror.items.list.white.ArmorPiercingRounds;
import znick_.riskofrain2.api.ror.items.list.white.BisonSteak;
import znick_.riskofrain2.api.ror.items.list.white.BustlingFungus;
import znick_.riskofrain2.api.ror.items.list.white.CritGlasses;
import znick_.riskofrain2.api.ror.items.list.white.Crowbar;
import znick_.riskofrain2.api.ror.items.list.white.FocusCrystal;
import znick_.riskofrain2.api.ror.items.list.white.Gasoline;
import znick_.riskofrain2.api.ror.items.list.white.GoatHoof;
import znick_.riskofrain2.api.ror.items.list.white.MonsterTooth;
import znick_.riskofrain2.api.ror.items.list.white.PersonalShield;
import znick_.riskofrain2.api.ror.items.list.white.RepulsionArmorPlate;
import znick_.riskofrain2.api.ror.items.list.white.RustedKey;
import znick_.riskofrain2.api.ror.items.list.white.SoldierSyringe;
import znick_.riskofrain2.api.ror.items.list.white.TougherTimes;
import znick_.riskofrain2.api.ror.items.list.white.TriTipDagger;
import znick_.riskofrain2.api.ror.items.list.white.cautiousslug.CautiousSlugItem;
import znick_.riskofrain2.api.ror.items.list.white.energydrink.EnergyDrinkItem;
import znick_.riskofrain2.api.ror.items.list.white.medkit.MedkitItem;
import znick_.riskofrain2.api.ror.items.list.white.stungrenade.StunGrenadeItem;
import znick_.riskofrain2.api.ror.items.list.white.warbanner.WarbannerItem;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;
import znick_.riskofrain2.item.armor.ArmorPiece;
import znick_.riskofrain2.item.armor.ArmorType;
import znick_.riskofrain2.util.RandomGenerator;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2CreativeTabs;

public class RiskOfRain2Items {
	
	/**
	 * The set of all items in the Risk Of Rain 2 game.
	 */
	public static final Set<RiskOfRain2Item> ITEM_SET = new LinkedHashSet<RiskOfRain2Item>();
	
	public static final Map<RiskOfRain2Item, AchievementItem> LOCKED_ITEMS = new LinkedHashMap<>();
	public static final Map<RiskOfRain2Item, AchievementItem> UNLOCKED_ITEMS = new LinkedHashMap<>();
	
	public static final Item HUNTRESS_HELMET = new ArmorPiece(PlayableCharacter.HUNTRESS, ArmorType.HELMET, "huntress");
	public static final Item HUNTRESS_CHESTPLATE = new ArmorPiece(PlayableCharacter.HUNTRESS, ArmorType.CHESTPLATE, "huntress");
	public static final Item HUNTRESS_LEGGINGS = new ArmorPiece(PlayableCharacter.HUNTRESS, ArmorType.LEGGINGS, "huntress");
	public static final Item HUNTESS_BOOTS = new ArmorPiece(PlayableCharacter.HUNTRESS, ArmorType.BOOTS, "huntress");
	
	public static final Item RAW_HUNTRITE = new BasicItem("Raw Huntrite", "character/huntress/", RiskOfRain2CreativeTabs.MISC, EnumChatFormatting.RED);
	public static final Item HUNTRITE_PLATE = new BasicItem("Huntrite Plate", "character/huntress/", RiskOfRain2CreativeTabs.MISC, EnumChatFormatting.RED);
	public static final Item TRESSIUM_PLATE = new BasicItem("Tressium Plate", "character/huntress/", RiskOfRain2CreativeTabs.MISC, EnumChatFormatting.DARK_BLUE);
	public static final Item LENS_FRAME = new BasicItem("Lens Frame", "character/huntress/", RiskOfRain2CreativeTabs.MISC);
	public static final Item LENS = new BasicItem("Lens", "character/huntress", RiskOfRain2CreativeTabs.MISC);
	public static final Item LIGHT_BLUE_LENS = new BasicItem("Light Blue Lens", "character/huntress", RiskOfRain2CreativeTabs.MISC);
	public static final Item LIGHT_BLUE_FRAMED_LENS = new BasicItem("Light Blue Framed Lens", "character/huntress", RiskOfRain2CreativeTabs.MISC);
	public static final Item LIGHT_BLUE_FRAMED_LENS_PAIR = new BasicItem("Light Blue Framed Lens Pair", "character/huntress", RiskOfRain2CreativeTabs.MISC, EnumRarity.rare);
	
	//White Items
	
	/**
	 * The "repulsion armor plate" item. Each time the player is hurt, they take 1 (+1 per item) half-heart less
	 * damage. m
	 */
	public static final Item REPULSION_ARMOR_PLATE = new RepulsionArmorPlate();
	/**
	 * The "tougher times" item. Gives the player a 15% (+15% per item) chance to take no damage upon getting hurt.
	 * Stacks hyperbolically instead of linearly, meaning it is impossible to reach 100% block chance. 
	 */
	public static final Item TOUGHER_TIMES = new TougherTimes();
	/**
	 * The "tri-tip dagger" item. Gives the player a 15% (+15% per item) chance per hit to apply wither 2 to
	 * the attacked enemy for 5 seconds.
	 */
	public static final Item TRI_TIP_DAGGER = new TriTipDagger();
	/**
	 * The "armor piercing rounds" item. Causes the player to deal 20% (+20% per item) more damage to bosses,
	 * such as the Ender Dragon or the Wither. May not work on modded bosses depending on how they are coded. 
	 */
	public static final Item ARMOR_PIERCING_ROUNDS = new ArmorPiercingRounds();
	/**
	 * The "Len's-Maker Glasses" item. Adds a 10% chance (+10% per item) for an attack to land a critical hit, 
	 * dealing double damage. 
	 */
	public static final Item CRIT_GLASSES = new CritGlasses();
	/**
	 * The "crowbar" item. Activates when the player attacks an enemy with over 90% health. The hit will deal
	 * 75% (+75% per item) more damage. 
	 */
	public static final Item CROWBAR = new Crowbar();
	public static final Item BISON_STEAK = new BisonSteak();
	/**
	 * The "cautious slug" item. Activates when the player does not take damage for 7 seconds. Begins healing the
	 * player at a rate of 1 (/2 per item) second per half-heart. Deactivates when the player takes damage.
	 */
	public static final Item CAUTIOUS_SLUG = new CautiousSlugItem();
	/**
	 * The "Paul's goat hoof" item. Adds 14% (+14% per item) boost to movement speed.
	 */
	public static final Item GOAT_HOOF = new GoatHoof();
	/**
	 * The "gasoline" item. When the player kills an enemy, all enemies around the killed enemy are lit on fire
	 * in a radius of 5 (+1 per item) blocks. The enemies are lit for 2 (+1 per item) seconds. 
	 */
	public static final Item GASOLINE = new Gasoline();
	/**
	 * The "medkit" item. Heals the player 2 seconds after they take damage for 2 (+1 per item) hearts.
	 */
	public static final Item MEDKIT = new MedkitItem();
	public static final Item BUSTLING_FUNGUS = new BustlingFungus();
	/**
	 * The "focus crystal" item. Causes the player to deal 20% (+20% per stack) more damage to enemies within 
	 * 4 blocks of the player.
	 */
	public static final Item FOCUS_CRYSTAL = new FocusCrystal();
	public static final Item PERSONAL_SHIELD = new PersonalShield();
	/**
	 * The "white scrap" item. Does nothing, but is taken first when using white 3D printers.
	 */
	public static final Item WHITE_SCRAP = new ScrapItem(ItemRarity.WHITE);
	/**
	 * The "energy drink" item. Adds 25% (+25% per item) movement speed to the player while sprinting.
	 */
	public static final Item ENERGY_DRINK = new EnergyDrinkItem();
	/**
	 * The "stun grenade" item. Has a 5% (+5% per item) chance to stun enemies in place for 2 seconds. 
	 */
	public static final Item STUN_GRENADE = new StunGrenadeItem();
	public static final Item SOLDIER_SYRINGE = new SoldierSyringe();
	/**
	 * The "monster tooth" item. The player is healed when consuming XP.
	 */
	public static final Item MONSTER_TOOTH = new MonsterTooth();
	/**
	 * The "rusted key" item. Allows the player to open rusty lockboxes.
	 */
	public static final Item RUSTED_KEY = new RustedKey();
	public static final Item WAR_BANNER = new WarbannerItem();
	
	//Green Items
	public static final Item FUEL_CELL = new FuelCell();
	/**
	 * The "will-o-the wisp" item. When an enemy dies, it explodes.
	 */
	public static final Item WILL_O_THE_WISP = new WillOTheWisp();
	/**
	 * The "hopoo feather" item. Allows the player 1 (+1 per stack) extra jump while mid-air.
	 */
	public static final Item HOPOO_FEATHER = new HopooFeather();
	/**
	 * The "harvesters scythe" item. When the player lands a critical attack, The player is healed 1 heart
	 * (+1 per item).
	 */
	public static final Item HARVESTERS_SCYTHE = new HarvestersScythe();
	public static final Item INFUSION = new InfusionItem();
	/**
	 * The "wax quail" item. Boosts the player forward when jumping while springing.
	 */
	public static final Item WAX_QUAIL = new WaxQuail();
	/**
	 * The "old war stealthkit" item. Falling below 25% health causes the player to become invisible and gain
	 * 40% movement speed for 5 seconds. Recharges every 30 seconds (-50% per item).
	 */
	public static final Item OLD_WAR_STEALTHKIT = new OldWarStealthkit();
	/**
	 * The "green scrap" item. Does nothing, but is taken first when using green 3D printers.
	 */
	public static final Item GREEN_SCRAP = new ScrapItem(ItemRarity.GREEN);
	/**
	 * The "leeching seed" item. Heals 1 (+1 per item) half hearts upon dealing damage.
	 */
	public static final Item LEECHING_SEED = new LeechingSeed();
	/**
	 * The "chronobauble" item. Gives enemies the slowness potion effect on hit. 
	 */
	public static final Item CHRONOBAUBLE = new Chronobauble();
	
	//Red Items
	public static final Item ALIEN_HEAD = new AlienHead();
	/**
	 * The "brilliant behemoth" item. All of the players attacks explode in a radius of 2 (+1 per item).
	 */
	public static final Item BRILLIANT_BEHEMOTH = new BrilliantBehemothItem();
	public static final Item FIFTY_SEVEN_LEAF_CLOVER = new FiftySevenLeafClover();
	/**
	 * The "Dio's best friend" item. Allows the player to be saved from death followed by 10 seconds of
	 * invincibility. Consumed on use.
	 */
	public static final Item DIOS_BEST_FRIEND = new DiosBestFriend();
	/**
	 * The "H3AD-5T v2" item. Makes the player jump significantly higher. 
	 */
	public static final Item HEAD_SET = new HeadSet(); //TODO: Add headset slam
	
	/**
	 * The "rejuvination rack" item. Adds 100% (+100% per stack) to all healing. 
	 */
	public static final Item REJUVINATION_RACK = new RejuvinationRack();
	/**
	 * The "red scrap" item. Does nothing, but is taken first when using red 3D printers.
	 */
	public static final Item RED_SCRAP = new ScrapItem(ItemRarity.RED);
	public static final Item HARDLIGHT_AFTERBURNER = new HardlightAfterburner();
	
	//Boss Items
	public static final Item PEARL = new PearlItem();
	public static final Item TITANIC_KNURL = new TitanicKnurlItem();
	public static final Item BOSS_SCRAP = new ScrapItem(ItemRarity.BOSS);
	
	//Lunar Items
	public static final Item SHAPED_GLASS = new ShapedGlass();
	public static final Item PURITY = new Purity();
	/**
	 * The "beads of fealty" item. Does nothing, but takes the player to the "A Moment, Whole" realm when ending the
	 * game. 
	 */
	public static final Item BEADS_OF_FEALTY = new BeadsOfFealty();	
	
	//Active Equipment Items
	public static final Item FOREIGN_FRUIT = new ForeignFruitItem();
	public static final Item SUPER_MASSIVE_LEECH = new SuperMassiveLeechItem();
	public static final Item JADE_ELEPHANT = new JadeElephantItem();
	public static final Item OCULAR_HUD = new OcularHudItem();
	
	/**
	 * Registers all items into the game.
	 */
	public static final void registerItems() {
		try {
			for (Field field : RiskOfRain2Items.class.getDeclaredFields()) {
				if (field.get(null) instanceof Item) {
					Item item = (Item) field.get(null);
					if (item instanceof RiskOfRain2Item) ITEM_SET.add((RiskOfRain2Item) item);
					GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
				}
			}
		} catch(Exception e) {throw new RuntimeException(e);}
		
		for (RiskOfRain2Item item : ITEM_SET) {
			LOCKED_ITEMS.put(item, new AchievementItem((RiskOfRain2Item) item, true));
			UNLOCKED_ITEMS.put(item, new AchievementItem((RiskOfRain2Item) item, false));
			RandomGenerator.registerItem(item);
		}
		for (AchievementItem item : LOCKED_ITEMS.values()) GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		for (AchievementItem item : UNLOCKED_ITEMS.values()) GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
