package znick_.riskofrain2.client.gui;

import java.awt.Color;

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
		super(id, (int) (x/scale), (int) (y/scale), width, height, "");
		this.texture = new ResourceLocation(texture);
		this.scale = scale;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			FontRenderer fontrenderer = mc.fontRenderer;
			mc.getTextureManager().bindTexture(this.texture);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			
			mouseX /= scale;
			mouseY /= scale;
			this.field_146123_n =
					mouseX >= this.xPosition && 
					mouseY >= this.yPosition && 
					mouseX < this.xPosition + this.width && 
					mouseY < this.yPosition + this.height;
					
			int k = this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			GL11.glPushMatrix();
			GL11.glScaled(this.scale, this.scale, this.scale);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, this.width, this.height);
			GL11.glPopMatrix();
			
			this.mouseDragged(mc, mouseX, mouseY);		
		}
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		mouseX /= this.scale;
		mouseY /= this.scale;
        return this.enabled && this.visible && 
        		mouseX >= this.xPosition && 
				mouseY >= this.yPosition && 
				mouseX < this.xPosition + this.width && 
				mouseY < this.yPosition + this.height;
    }

}
