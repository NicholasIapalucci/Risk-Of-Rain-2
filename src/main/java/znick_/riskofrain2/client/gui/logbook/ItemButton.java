package znick_.riskofrain2.client.gui.logbook;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class ItemButton extends GuiButton {

	private final RiskOfRain2Item item;

	private boolean playedSound;
	private double scale;

	private final ResourceLocation backgroundTexture;
	private final ResourceLocation itemTexture;

	public ItemButton(int id, RiskOfRain2Item item, int x, int y, double scale) {
		super(id, x, y, 256, 256, "");
		this.item = item;
		this.scale = scale;
		this.backgroundTexture = new ResourceLocation(RiskOfRain2Mod.MODID + ":textures/gui/logbook/"
				+ item.getRarity().name().toLowerCase() + "_background.png");
		this.itemTexture = item.getTexture();
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		return this.enabled && this.visible && this.field_146123_n;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			FontRenderer fontrenderer = mc.fontRenderer;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			mouseX /= this.scale;
			mouseY /= this.scale;
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

			if (isSelected) {
				if (!this.playedSound) {
					mc.getSoundHandler().playSound(
							PositionedSoundRecord.func_147674_a(new ResourceLocation("ror2", "button_hover"), 1.0F));
					this.playedSound = true;
				}
			}

			else
				this.playedSound = false;

			GL11.glPushMatrix();
			GL11.glScaled(this.scale, this.scale, this.scale);
			mc.getTextureManager().bindTexture(this.backgroundTexture);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, this.width, this.height);
			mc.getTextureManager().bindTexture(this.itemTexture);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, this.width, this.height);
			GL11.glPopMatrix();

			this.mouseDragged(mc, mouseX, mouseY);
		}
	}

	public RiskOfRain2Item getItem() {
		return this.item;
	}

}
