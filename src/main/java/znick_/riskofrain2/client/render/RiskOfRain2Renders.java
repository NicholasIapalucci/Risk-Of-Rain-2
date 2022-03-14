package znick_.riskofrain2.client.render;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.init.Items;
import znick_.riskofrain2.api.ror.enemy.EliteType;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.HuntressRainingArrow;
import znick_.riskofrain2.block.itemgen.printer.Render3DPrinter;
import znick_.riskofrain2.block.itemgen.printer.TileEntity3DPrinter;
import znick_.riskofrain2.client.render.character.huntress.RenderHuntressRainingArrow;
import znick_.riskofrain2.client.render.entity.elite.RenderEliteZombie;
import znick_.riskofrain2.entity.elite.zombie.EntityBlazingZombie;
import znick_.riskofrain2.item.ror.list.white.fireworks.FireworkEntity;

public class RiskOfRain2Renders {

	public static void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBlazingZombie.class, new RenderEliteZombie(EliteType.BLAZING));
		RenderingRegistry.registerEntityRenderingHandler(HuntressRainingArrow.class, new RenderHuntressRainingArrow());
		RenderingRegistry.registerEntityRenderingHandler(FireworkEntity.class, new RenderSnowballWithRotation(Items.fireworks));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntity3DPrinter.class, new Render3DPrinter());
	}
}
