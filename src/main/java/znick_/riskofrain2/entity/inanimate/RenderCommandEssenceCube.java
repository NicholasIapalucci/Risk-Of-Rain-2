package znick_.riskofrain2.entity.inanimate;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.client.render.RenderHelper;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class RenderCommandEssenceCube extends Render {

	private final ItemRarity rarity;
	
	public RenderCommandEssenceCube(ItemRarity rarity) {
		this.rarity = rarity;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float mouseX, float mouseY) {
		CommandEssenceCube cube = (CommandEssenceCube) entity;
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.getEntityTexture(entity));
		RenderHelper.renderCube(x - cube.getSize()/2, y, z - cube.getSize()/2, cube.getSize());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.TEXTURES + "entity/items/command_essence/" + this.rarity.toString().toLowerCase());
	}
}
