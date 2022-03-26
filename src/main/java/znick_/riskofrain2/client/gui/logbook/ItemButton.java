package znick_.riskofrain2.client.gui.logbook;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.client.render.RenderHelper;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class ItemButton extends GuiButton {

	private final RiskOfRain2Item item;

	private boolean playedSound;
	private boolean requiresUnlock = true;
	
	private final ResourceLocation backgroundTexture;
	private final ResourceLocation itemTexture;

	public ItemButton(int id, RiskOfRain2Item item, int x, int y, int w, int h) {
		super(id, x, y, w, h, "");
		this.item = item;
		this.backgroundTexture = PlayerData.get(Minecraft.getMinecraft().thePlayer).hasFound(item)? RiskOfRain2Resources.get(RiskOfRain2Mod.MODID + ":textures/gui/logbook/" + item.getRarity().name().toLowerCase() + "_background.png") : RiskOfRain2Resources.get(RiskOfRain2Mod.MODID + ":textures/gui/logbook/unfound_item_background.png");
		this.itemTexture = PlayerData.get(Minecraft.getMinecraft().thePlayer).hasUnlocked(item)? item.getTexture(): RiskOfRain2Resources.get(RiskOfRain2Mod.MODID + ":textures/gui/logbook/locked_item.png");
	}
	
	public ItemButton(int id, RiskOfRain2Item item, int x, int y, int w, int h, boolean requiresUnlock) {
		super(id, x, y, w, h, "");
		this.item = item;
		this.backgroundTexture = RiskOfRain2Resources.get(RiskOfRain2Mod.MODID + ":textures/gui/logbook/" + item.getRarity().name().toLowerCase() + "_background.png");
		this.itemTexture = item.getTexture();
		this.requiresUnlock = requiresUnlock;
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		return this.enabled && this.visible && this.field_146123_n;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			FontRenderer fontrenderer = mc.fontRenderer;

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

			// Play the sound if necessary
			if (isSelected) {
				if (!this.playedSound) {
					mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("ror2", "button_hover"), 1.0F));
					this.playedSound = true;
				}
			}
			else this.playedSound = false;

			// Apply transformations
			GL11.glPushMatrix();
			GL11.glColor3f(1, 1, 1);
			
			// Draw the background
			mc.getTextureManager().bindTexture(this.backgroundTexture);
			RenderHelper.drawRect2D(this.xPosition, this.yPosition, this.width, this.height);
			
			// Black out the item if the player hasnt found it
			if (this.requiresUnlock && !PlayerData.get(Minecraft.getMinecraft().thePlayer).hasFound(this.item)) GL11.glColor3f(0, 0, 0);
			else GL11.glColor3f(1, 1, 1);

			// Draw the item
			mc.getTextureManager().bindTexture(this.itemTexture);
			RenderHelper.drawRect2D(this.xPosition, this.yPosition, this.width, this.height);
			
			// Exit the transformations and update the mouse
			GL11.glPopMatrix();
			this.mouseDragged(mc, mouseX, mouseY);
		}
	}

	public RiskOfRain2Item getItem() {
		return this.item;
	}

}
