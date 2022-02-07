package znick_.riskofrain2.entity;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.HuntressRainingArrow;
import znick_.riskofrain2.client.render.character.huntress.RenderHuntressRainingArrow;
import znick_.riskofrain2.entity.elite.zombie.EntityBlazingZombie;

public class RiskOfRain2Entities {

	public static void registerEntities() {
		EntityRegistry.registerModEntity(HuntressRainingArrow.class, "huntress_raining_arrow", 0, RiskOfRain2.instance, 64, 10, true);
		
		EntityRegistry.registerModEntity(EntityBlazingZombie.class, "blazing_zombie", 1, RiskOfRain2.instance, 64, 10, true);
	}
}
