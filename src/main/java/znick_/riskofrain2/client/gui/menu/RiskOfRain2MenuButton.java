package znick_.riskofrain2.client.gui.menu;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.client.gui.TexturedGuiButton;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class RiskOfRain2MenuButton extends TexturedGuiButton {

	private boolean playedSound;
	private final ResourceLocation hoverTexture;
	
	public RiskOfRain2MenuButton(int id, int x, int y, int width, int height, double scale, String label) {
		super(id, x, y, width, height, scale, "ror2:textures/gui/menu/" + label + ".png");
		this.hoverTexture = RiskOfRain2Resources.get("ror2:textures/gui/menu/" + label + "_selected.png");
	}
	
	@Override
	public void func_146113_a(SoundHandler sound) {
        sound.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("ror2", "button_press"), 1.0F));
    }
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {	
		if (this.visible) {
			FontRenderer fontrenderer = mc.fontRenderer;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			
			mouseX /= scale;
			mouseY /= scale;
			this.field_146123_n =
					mouseX >= this.xPosition && 
					mouseY >= this.yPosition && 
					mouseX < this.xPosition + this.width && 
					mouseY < this.yPosition + this.height;
			
			boolean isSelected = this.getHoverState(this.field_146123_n) == 2;
					
			int k = this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			GL11.glPushMatrix();
			GL11.glScaled(this.scale, this.scale, this.scale);
			
			if (isSelected) {
				mc.getTextureManager().bindTexture(this.hoverTexture);
				this.drawRectOutline(this.xPosition - 3, this.yPosition - 4, this.xPosition + this.width + 2, this.yPosition + this.height + 2, 2, Color.WHITE.getRGB());
				if (!this.playedSound) {
					mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("ror2", "button_hover"), 1.0F));
					this.playedSound = true;
				}
			} 
			
			else {
				mc.getTextureManager().bindTexture(this.texture);
				this.playedSound = false;
			}
			
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, this.width, this.height);
			
			
			GL11.glPopMatrix();
			
			this.mouseDragged(mc, mouseX, mouseY);		
		}
	}
	
	private void drawRectOutline(int x1, int y1, int x2, int y2, int thickness, int color) {
		this.drawRect(x1, y1, x1 + thickness, y2, color); // LEFT
		this.drawRect(x1, y1, x2, y1 + thickness, color); // TOP
		this.drawRect(x1, y2, x2, y2 + thickness, color); // BOTTOM
		this.drawRect(x2, y1, x2 + thickness, y2 + thickness, color); // RIGHT
	}
}
