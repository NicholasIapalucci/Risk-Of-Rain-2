package znick_.riskofrain2.client.render;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import znick_.riskofrain2.api.ror.enemy.EliteType;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.HuntressRainingArrow;
import znick_.riskofrain2.block.printer.Render3DPrinter;
import znick_.riskofrain2.block.printer.TileEntity3DPrinter;
import znick_.riskofrain2.client.render.character.huntress.RenderHuntressRainingArrow;
import znick_.riskofrain2.client.render.entity.elite.RenderEliteZombie;
import znick_.riskofrain2.entity.elite.zombie.EntityBlazingZombie;

public class RiskOfRain2Renders {

	public static void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBlazingZombie.class, new RenderEliteZombie(EliteType.BLAZING));
		RenderingRegistry.registerEntityRenderingHandler(HuntressRainingArrow.class, new RenderHuntressRainingArrow());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntity3DPrinter.class, new Render3DPrinter());
	}
}
