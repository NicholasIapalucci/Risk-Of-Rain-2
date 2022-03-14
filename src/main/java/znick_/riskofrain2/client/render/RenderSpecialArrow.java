package znick_.riskofrain2.client.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class RenderSpecialArrow extends Render {

	private final ResourceLocation texture;
	private final Item item;

	public RenderSpecialArrow(String texture, Item item) {
		this.texture = new ResourceLocation(texture);
		this.item = item;
	}

	public void doRender(Entity entity, double x, double y, double z, float p4, float p5) {
		IIcon iicon = this.item.getIconFromDamage(0);

		if (iicon != null) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			this.bindEntityTexture(entity);
			Tessellator tessellator = Tessellator.instance;

			this.renderItem(tessellator, entity, iicon);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return TextureMap.locationItemsTexture;
	}

	private void renderItem(Tessellator t, Entity entity, IIcon icon) {
		
		float f = icon.getMinU();
		float f1 = icon.getMaxU();
		float f2 = icon.getMinV();
		float f3 = icon.getMaxV();
		
		float f4 = 1.0F;
		float f5 = 0.5F;
		float f6 = 0.25F;
		
		GL11.glRotatef(entity.rotationPitch, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entity.rotationYaw, 1.0F, 0.0F, 0.0F);
		
		t.startDrawingQuads();
		t.setNormal(0.0F, 1.0F, 0.0F);
		t.addVertexWithUV((double) (0.0F - f5), (double) (0.0F - f6), 0.0D, (double) f, (double) f3);
		t.addVertexWithUV((double) (f4 - f5), (double) (0.0F - f6), 0.0D, (double) f1, (double) f3);
		t.addVertexWithUV((double) (f4 - f5), (double) (f4 - f6), 0.0D, (double) f1, (double) f2);
		t.addVertexWithUV((double) (0.0F - f5), (double) (f4 - f6), 0.0D, (double) f, (double) f2);
		t.draw();
	}
}
