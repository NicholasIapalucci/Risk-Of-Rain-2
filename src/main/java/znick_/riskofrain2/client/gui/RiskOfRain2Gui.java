package znick_.riskofrain2.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.GuiIngameForge;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.survivor.Survivor;

public class RiskOfRain2Gui extends Gui {

	private int width;
	private int height;
	
	public RiskOfRain2Gui() {
		if (Minecraft.getMinecraft().thePlayer == null) return;

		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayHeight, Minecraft.getMinecraft().displayHeight);
		this.width = res.getScaledWidth();
		this.height = res.getScaledHeight();
		
		this.renderBuffs();
		this.renderCrosshair();
	}
	
	private void renderBuffs() {
		int i = 0;
		for (Buff buff : PlayerData.get(Minecraft.getMinecraft().thePlayer).getBuffs()) {
			if (buff.getIconTexture() == null) continue;
			GL11.glPushMatrix();
			Minecraft.getMinecraft().getTextureManager().bindTexture(buff.getIconTexture());
			GL11.glColor3f(1, 1, 1);
			GL11.glScaled(0.1, 0.1, 0.1);
			this.drawTexturedModalRect(40 + i, 40, 0, 0, 256, 256);
			i++;
			GL11.glPopMatrix();
		}
	}
	
	private void renderCrosshair() {
		for (Survivor survivor : Survivor.getSurvivors()) {
			if (survivor.isPlayer(Minecraft.getMinecraft().thePlayer)) {
				survivor.renderCrosshair(this);
				return;
			}
		}
		
		//If the player isnt a survivor, render crosshairs normally.
		GuiIngameForge.renderCrosshairs = true;
	}
}
