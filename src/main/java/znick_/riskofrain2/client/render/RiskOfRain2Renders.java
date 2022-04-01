package znick_.riskofrain2.client.render;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.init.Items;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.HuntressRainingArrow;
import znick_.riskofrain2.block.itemgen.printer.Render3DPrinter;
import znick_.riskofrain2.block.itemgen.printer.TileEntity3DPrinter;
import znick_.riskofrain2.client.render.character.huntress.RenderHuntressRainingArrow;
import znick_.riskofrain2.client.render.entity.RenderEntity2D;
import znick_.riskofrain2.entity.elite.EliteType;
import znick_.riskofrain2.entity.elite.mobs.zombie.BlazingZombie;
import znick_.riskofrain2.entity.elite.mobs.zombie.GlacialZombie;
import znick_.riskofrain2.entity.elite.mobs.zombie.MalachiteZombie;
import znick_.riskofrain2.entity.elite.mobs.zombie.RenderEliteZombie;
import znick_.riskofrain2.entity.inanimate.CommandEssenceCube;
import znick_.riskofrain2.entity.inanimate.CommandEssenceEntity;
import znick_.riskofrain2.entity.inanimate.RenderCommandEssence;
import znick_.riskofrain2.entity.inanimate.RenderCommandEssenceCube;
import znick_.riskofrain2.item.ror.list.green.atgmissile.ATGMissileEntity;
import znick_.riskofrain2.item.ror.list.red.ceremonialdagger.CeremonialDaggerEntity;
import znick_.riskofrain2.item.ror.list.white.fireworks.FireworkEntity;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class RiskOfRain2Renders {

	public static void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(BlazingZombie.class, new RenderEliteZombie(EliteType.BLAZING));
		RenderingRegistry.registerEntityRenderingHandler(GlacialZombie.class, new RenderEliteZombie(EliteType.GLACIAL));
		RenderingRegistry.registerEntityRenderingHandler(MalachiteZombie.class, new RenderEliteZombie(EliteType.MALACHITE));
		
		RenderingRegistry.registerEntityRenderingHandler(HuntressRainingArrow.class, new RenderHuntressRainingArrow());
		
		RenderingRegistry.registerEntityRenderingHandler(FireworkEntity.class, new RenderEntity2D(Items.fireworks));
		RenderingRegistry.registerEntityRenderingHandler(CommandEssenceEntity.class, new RenderCommandEssence(ItemRarity.WHITE));
		RenderingRegistry.registerEntityRenderingHandler(CommandEssenceCube.class, new RenderCommandEssenceCube());
		RenderingRegistry.registerEntityRenderingHandler(CeremonialDaggerEntity.class, new RenderEntity2D("ror2:textures/entity/items/ceremonial_dagger"));
		RenderingRegistry.registerEntityRenderingHandler(ATGMissileEntity.class, new RenderEntity2D("ror2:textures/entity/items/atg_missile"));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntity3DPrinter.class, new Render3DPrinter());
	}
}
