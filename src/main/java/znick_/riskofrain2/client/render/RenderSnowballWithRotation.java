package znick_.riskofrain2.client.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class RenderSnowballWithRotation extends Render {

	private Item item;
	private int metadata;

	public RenderSnowballWithRotation(Item item, int meta) {
        this.item = item;
        this.metadata = meta;
    }

	public RenderSnowballWithRotation(Item item) {
        this(item, 0);
    }

	public void doRender(Entity entity, double x, double y, double z, float float1, float float2) {
		IIcon iicon = this.item.getIconFromDamage(this.metadata);

		if (iicon != null) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			this.bindEntityTexture(entity);
			Tessellator tessellator = Tessellator.instance;

			if (iicon == ItemPotion.func_94589_d("bottle_splash")) {
				int i = PotionHelper.func_77915_a(((EntityPotion) entity).getPotionDamage(), false);
				float f2 = (float) (i >> 16 & 255) / 255.0F;
				float f3 = (float) (i >> 8 & 255) / 255.0F;
				float f4 = (float) (i & 255) / 255.0F;
				GL11.glColor3f(f2, f3, f4);
				GL11.glPushMatrix();
				this.renderItem(tessellator, ItemPotion.func_94589_d("overlay"), entity);
				GL11.glPopMatrix();
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
			}

			this.renderItem(tessellator, iicon, entity);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless
	 * you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TextureMap.locationItemsTexture;
	}

	private void renderItem(Tessellator tessellator, IIcon icon, Entity entity) {
		
		// Assign some local variables
		float f = icon.getMinU();
		float f1 = icon.getMaxU();
		float f2 = icon.getMinV();
		float f3 = icon.getMaxV();
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
