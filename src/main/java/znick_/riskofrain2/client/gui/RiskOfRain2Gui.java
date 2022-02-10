package znick_.riskofrain2.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.Loadout;

public class RiskOfRain2Gui extends Gui {

	private final int width;
	private final int height;
	
	public RiskOfRain2Gui() {
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayHeight, Minecraft.getMinecraft().displayHeight);
		this.width = res.getScaledWidth();
		this.height = res.getScaledHeight();
		
		if (Minecraft.getMinecraft().thePlayer == null) return;
		
		this.renderBuffs();
		this.renderCrosshair();
		
		Loadout loadout = PlayerData.get(Minecraft.getMinecraft().thePlayer).getLoadout();
		this.renderAbility(loadout.getUtility(), this.width/3, this.height - 24, 12);
		this.renderAbility(loadout.getSpecial(), this.width/3 + 24, this.height - 24, 12); // TODO: CAUSING ISSUES
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
	
	private void renderAbility(Class<? extends Ability> abilityClass, int x, int y, int s) {
		try {
			Ability ability = abilityClass.newInstance();
			ResourceLocation texture = ability.getTexture();
			GL11.glPushMatrix();
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			GL11.glColor3f(1, 1, 1);
			GL11.glScaled(1d/s, 1d/s, 1d/s);
			this.drawTexturedModalRect(x * s, y * s, 0, 0, 256, 256);
			GL11.glPopMatrix();
		} 
		
		catch(Exception e) {
			throw new RuntimeException(e);
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
