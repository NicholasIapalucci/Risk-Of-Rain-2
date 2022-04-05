package znick_.riskofrain2.entity.elite;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

/**
 * Interface for rendering elite entities. Render classes should extend the default entity renderer
 * and implement this. For example, the elite zombie renderer is declared as follows:
 * <br><br>
 * 
 * &emsp; {@code public class RenderEliteZombie extends RenderZombie implements RenderEliteEntity}
 * 
 * <br><br>
 * This allows for classes to just extend vanilla renders instead of having to rewrite them.
 * 
 * @author zNick_
 */
public interface RenderEliteEntity {

	/**
	 * Renders the entity's name with the elite icon next to it. 
	 * 
	 * @param entity The entity to render the name of
	 * @param name The name of the entity
	 * @param x The x-coordinate to render at
	 * @param y The y-coordiante to render at
	 * @param z The z-coordinate to render at
	 * @param dist The distance
	 */
	public default void renderEntityName(Entity entity, RenderManager rm, String name, double x, double y, double z, int dist) {
		Render render = (Render) this;
		double d3 = entity.getDistanceSqToEntity(rm.livingPlayer);

		if (d3 <= (double) (dist * dist)) {
			FontRenderer fontrenderer = render.getFontRendererFromRenderManager();
			float f = 1.6F;
			float f1 = 0.016666668F * f;
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.0F, (float) y + entity.height + 0.5F, (float) z);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-rm.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(rm.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(-f1, -f1, f1);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDepthMask(false);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			Tessellator tessellator = Tessellator.instance;
			
			byte b0 = 0;
			if (name.equals("deadmau5")) b0 = -10;

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			int j = fontrenderer.getStringWidth(name) / 2;
			
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
			tessellator.addVertex((double) (-j - 1), (double) (-1 + b0), 0.0D);
			tessellator.addVertex((double) (-j - 1), (double) ( 8 + b0), 0.0D);
			tessellator.addVertex((double) ( j + 1), (double) ( 8 + b0), 0.0D);
			tessellator.addVertex((double) ( j + 1), (double) (-1 + b0), 0.0D);
			tessellator.draw();
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			fontrenderer.drawString(name, -fontrenderer.getStringWidth(name) / 2, b0, 553648127);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			fontrenderer.drawString(name, -fontrenderer.getStringWidth(name) / 2, b0, -1);
			GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glDisable(GL11.GL_BLEND);
			
			this.renderEliteIcon(tessellator, -j - 10, -1, 9);
			GL11.glPopMatrix();
		}
	}
	
	/**
	 * Renders the small elite icon next to the enemy's name.
	 * 
	 * @param tessellator The tessellator to render with
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param d The dimensions of the icon
	 */
	public default void renderEliteIcon(Tessellator tessellator, double x, double y, double d) {
		String name = this.getEliteTypeForRender().getItem().getUnlocalizedName().substring(5);
		Minecraft.getMinecraft().getTextureManager().bindTexture(RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + name + ".png"));
		GL11.glPushMatrix();
		GL11.glColor3f(1, 1, 1);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x,     y,     0.0D, 0, 0);
		tessellator.addVertexWithUV(x,     y + d, 0.0D, 0, 1);
		tessellator.addVertexWithUV(x + d, y + d, 0.0D, 1, 1);
		tessellator.addVertexWithUV(x + d, y,     0.0D, 1, 0);
		tessellator.draw();
		
		GL11.glPopMatrix();
	}
	
	/**
	 * Returns the {@code EliteType} of this entity; Used by the renderer.
	 */
	public abstract EliteType getEliteTypeForRender();
}
