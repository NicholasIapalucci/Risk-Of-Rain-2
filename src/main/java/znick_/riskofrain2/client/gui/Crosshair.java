package znick_.riskofrain2.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import znick_.riskofrain2.RiskOfRain2;

public class Crosshair {

	private final ResourceLocation texture;
	private final double scale;
	
	public Crosshair(String texture, int scale) {
		this.texture = new ResourceLocation(RiskOfRain2.MODID + ":textures/gui/survivor/" + texture);
		this.scale = scale;
	}
	
	public ResourceLocation getTexture() {
		return this.texture;
	}
	
	public void render(Gui gui) {
		GuiIngameForge.renderCrosshairs = false;
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		int width = res.getScaledWidth();
		int height = res.getScaledHeight();
		
		GL11.glPushMatrix();
		GL11.glScaled(1d/this.scale, 1d/this.scale, 1d/this.scale);
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.getTexture());
		GL11.glColor3f(1, 1, 1);
		
		gui.drawTexturedModalRect((int) ((width/2) * this.scale - 128), (int) ((height/2) * this.scale - 128), 0, 0, 256, 256);
		
		GL11.glPopMatrix();
	}
}
