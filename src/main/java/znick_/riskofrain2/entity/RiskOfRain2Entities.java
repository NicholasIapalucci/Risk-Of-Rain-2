package znick_.riskofrain2.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.HuntressRainingArrow;
import znick_.riskofrain2.entity.elite.zombie.EntityBlazingZombie;
import znick_.riskofrain2.item.ror.list.white.fireworks.FireworkEntity;

public class RiskOfRain2Entities {

	public static void registerEntities() {
		EntityRegistry.registerModEntity(HuntressRainingArrow.class, "huntress_raining_arrow", 0, RiskOfRain2.instance, 64, 10, true);
		EntityRegistry.registerModEntity(FireworkEntity.class, "bundle_of_fireworks", 1, RiskOfRain2.instance, 64, 10, true);
		
		EntityRegistry.registerModEntity(EntityBlazingZombie.class, "blazing_zombie", 2, RiskOfRain2.instance, 64, 10, false);
	}
}
