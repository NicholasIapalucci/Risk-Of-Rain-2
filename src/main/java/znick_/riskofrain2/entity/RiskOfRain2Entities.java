package znick_.riskofrain2.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.HuntressRainingArrow;
import znick_.riskofrain2.entity.elite.mobs.zombie.BlazingZombie;
import znick_.riskofrain2.entity.elite.mobs.zombie.GlacialZombie;
import znick_.riskofrain2.entity.elite.mobs.zombie.MalachiteZombie;
import znick_.riskofrain2.entity.inanimate.CommandEssenceCube;
import znick_.riskofrain2.entity.inanimate.CommandEssenceEntity;
import znick_.riskofrain2.item.ror.list.red.ceremonialdagger.CeremonialDaggerEntity;
import znick_.riskofrain2.item.ror.list.white.fireworks.FireworkEntity;

public class RiskOfRain2Entities {

	private static int nextID = 0;
	
	public static void registerEntities() {
		registerStandardEntity(HuntressRainingArrow.class, "huntress_raining_arrow");
		registerStandardEntity(FireworkEntity.class, "bundle_of_fireworks");
		registerStandardEntity(CeremonialDaggerEntity.class, "ceremonial_dagger");
		registerStandardEntity(CommandEssenceEntity.class, "command_essence");
		registerStandardEntity(CommandEssenceCube.class, "command_essence_cube");
		
		// Elite Zombie
		registerStandardEntity(BlazingZombie.class, "blazing_zombie");
		registerStandardEntity(GlacialZombie.class, "glacial_zombie");
		registerStandardEntity(MalachiteZombie.class, "malachite_zombie");
	}
	
	private static void registerStandardEntity(Class<? extends Entity> entityClass, String name) {
		EntityRegistry.registerModEntity(entityClass, name, nextID++, RiskOfRain2Mod.instance, 64, 1, true);
	}
}
