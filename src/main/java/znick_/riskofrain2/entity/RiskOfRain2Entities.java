package znick_.riskofrain2.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.ror.artifact.list.command.entity.CommandEssenceCube;
import znick_.riskofrain2.api.ror.artifact.list.command.entity.CommandEssenceEntity;
import znick_.riskofrain2.api.ror.artifact.list.vengeance.VengeantPlayer;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.HuntressRainingArrow;
import znick_.riskofrain2.entity.elite.mobs.zombie.BlazingZombie;
import znick_.riskofrain2.entity.elite.mobs.zombie.GlacialZombie;
import znick_.riskofrain2.entity.elite.mobs.zombie.MalachiteZombie;
import znick_.riskofrain2.item.ror.list.green.atgmissile.ATGMissileEntity;
import znick_.riskofrain2.item.ror.list.red.ceremonialdagger.CeremonialDaggerEntity;
import znick_.riskofrain2.item.ror.list.white.fireworks.FireworkEntity;

/**
 * Class of custom entities in the Risk of Rain 2 mod.
 * 
 * @author zNick_
 */
public class RiskOfRain2Entities {

	/**The next unused ID*/
	private static int nextID = 0;
	
	/**
	 * Registers the custom entities into the game. 
	 */
	public static void registerEntities() {
		registerStandardEntity(HuntressRainingArrow.class, "huntress_raining_arrow");
		registerStandardEntity(FireworkEntity.class, "bundle_of_fireworks");
		registerStandardEntity(CeremonialDaggerEntity.class, "ceremonial_dagger");
		registerStandardEntity(ATGMissileEntity.class, "atg_missile");
		registerStandardEntity(CommandEssenceEntity.class, "command_essence");
		registerStandardEntity(CommandEssenceCube.class, "command_essence_cube");
		
		// Artifacts
		registerStandardEntity(VengeantPlayer.class, "vengeant_player");
		
		// Elite Zombie
		registerStandardEntity(BlazingZombie.class, "blazing_zombie");
		registerStandardEntity(GlacialZombie.class, "glacial_zombie");
		registerStandardEntity(MalachiteZombie.class, "malachite_zombie");
	}
	
	/**
	 * Registers an entity with standard parameters. 
	 * 
	 * @param entityClass The class of the entity to register
	 * @param name The name of the entity to register
	 */
	private static void registerStandardEntity(Class<? extends Entity> entityClass, String name) {
		EntityRegistry.registerModEntity(entityClass, name, nextID++, RiskOfRain2Mod.instance, 64, 1, true);
	}
}
