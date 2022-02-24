package znick_.riskofrain2.client.gui.menu;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class TexturedGuiButton extends GuiButton {

	protected final ResourceLocation texture;
	protected final double scale;
	
	public TexturedGuiButton(int id, int x, int y, int width, int height, double scale, String texture) {
		super(id, x, y, width, height, "");
		this.texture = new ResourceLocation(texture);
		this.scale = scale;
	}

	@Override
	public void drawButton(Minecraft par1, int par2, int par3) {
		if (this.visible) {
			FontRenderer fontrenderer = par1.fontRenderer;
			par1.getTextureManager().bindTexture(this.texture);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
			int k = this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			GL11.glPushMatrix();
			GL11.glScaled(this.scale, this.scale, this.scale);
			double s1 = 1d/this.scale;
			this.drawTexturedModalRect((int) (this.xPosition * s1), (int) (this.yPosition * s1), 0, 0, this.width, this.height);
			GL11.glPopMatrix();
			
			this.mouseDragged(par1, par2, par3);
			int l = 14737632;

			if (packedFGColour != 0) l = packedFGColour;
			else if (!this.enabled) l = 10526880;
			else if (this.field_146123_n) l = 16777120;
			
			
		}
	}

}
