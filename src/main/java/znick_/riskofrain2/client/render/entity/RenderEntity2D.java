package znick_.riskofrain2.client.render.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class RenderEntity2D extends Render {

	private IIcon icon;
	private Item item;
	private ResourceLocation texture;
	private int metadata;

	public RenderEntity2D(Item item, int meta) {
		this.item = item;
        this.metadata = meta;
    }

	public RenderEntity2D(Item item) {
        this(item, 0);
    }
	
	public RenderEntity2D(IIcon icon) {
		this.icon = icon;
	}
	
	public RenderEntity2D(String texture) {
		this.texture = RiskOfRain2Resources.get(texture);
	}

	public void doRender(Entity entity, double x, double y, double z, float float1, float float2) {
		this.icon = this.item.getIconFromDamage(this.metadata);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		this.bindEntityTexture(entity);
		Tessellator tessellator = Tessellator.instance;
		this.renderItem(tessellator, this.icon, entity);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless
	 * you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.texture == null? TextureMap.locationItemsTexture : this.texture;
	}

	private void renderItem(Tessellator tessellator, IIcon icon, Entity entity) {
		
		// Assign some local variables
		float f = this.texture == null? icon.getMinU() : 0;
		float f1 = this.texture == null? icon.getMaxU() : 1;
		float f2 = this.texture == null? icon.getMinV() : 0;
		float f3 = this.texture == null? icon.getMaxV() : 1;
		float f4 = 1.0F;
		float f5 = 0.5F;
		float f6 = 0.25F;
		
		// Perform some translations to rotate the item correctly
		GL11.glColor3f(1, 1, 1);
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F - entity.rotationYaw, 0.0F, 0.0F, 1.0F);
		
		// Render the item
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.addVertexWithUV((double) (0.0F - f5), (double) (0.0F - f6), 0.0D, (double) f, (double) f3);
		tessellator.addVertexWithUV((double) (f4 - f5), (double) (0.0F - f6), 0.0D, (double) f1, (double) f3);
		tessellator.addVertexWithUV((double) (f4 - f5), (double) (f4 - f6), 0.0D, (double) f1, (double) f2);
		tessellator.addVertexWithUV((double) (0.0F - f5), (double) (f4 - f6), 0.0D, (double) f, (double) f2);
		tessellator.draw();
	}
}
