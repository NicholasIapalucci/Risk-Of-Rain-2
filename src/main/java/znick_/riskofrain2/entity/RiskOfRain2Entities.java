package znick_.riskofrain2.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.HuntressRainingArrow;
import znick_.riskofrain2.entity.elite.mobs.zombie.BlazingZombie;
import znick_.riskofrain2.entity.elite.mobs.zombie.MalachiteZombie;
import znick_.riskofrain2.item.ror.list.white.fireworks.FireworkEntity;

public class RiskOfRain2Entities {

	private static int nextID = 0;
	
	public static void registerEntities() {
		EntityRegistry.registerModEntity(HuntressRainingArrow.class, "huntress_raining_arrow", nextID++, RiskOfRain2Mod.instance, 64, 10, true);
		EntityRegistry.registerModEntity(FireworkEntity.class, "bundle_of_fireworks", nextID++, RiskOfRain2Mod.instance, 64, 10, true);
		
		registerLivingEntity(BlazingZombie.class, "blazing_zombie");
		registerLivingEntity(MalachiteZombie.class, "malachite_zombie");
	}
	
	private static void registerLivingEntity(Class<? extends Entity> entityClass, String name) {
		EntityRegistry.registerModEntity(entityClass, name, nextID++, RiskOfRain2Mod.instance, 64, 1, true);
	}
}
