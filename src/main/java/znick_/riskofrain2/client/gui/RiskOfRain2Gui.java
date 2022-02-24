package znick_.riskofrain2.client.gui;

import java.awt.Color;
import java.util.Optional;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
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
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		this.width = res.getScaledWidth();
		this.height = res.getScaledHeight();
		
		if (Minecraft.getMinecraft().thePlayer == null) return;
		
		this.renderBuffs();
		this.renderCrosshair();
		this.renderHealth();
		this.renderAbilities();
	}
	
	/**
	 * Renders the custom Risk of Rain 2 health bar onto the screen.
	 */
	private void renderHealth() {
		if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) return;
		
		GuiIngameForge.renderHealth = false;
		GuiIngameForge.renderExperiance = false;
			
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		FontRenderer font = Minecraft.getMinecraft().fontRenderer;
		String health = (int) player.getHealth() + "/" + (int) player.getMaxHealth();
			
		int width = this.width/4;
		int left = this.width/20;
		int right = (int) (left + width * (player.getHealth() / player.getMaxHealth()));
		int top = this.height - 26;
		int bottom = this.height - 16;
			
		this.drawRect(left,      bottom + 1, left + width, top,     new Color(50,  50,  50).getRGB()); //Base
		this.drawRect(left,      bottom + 1, left + 1,     bottom,  new Color(67,  125, 26).getRGB()); //LCorner
		this.drawRect(left + 1,  bottom + 1, right,        bottom,  new Color(82,  150, 38).getRGB()); //Bottom
		this.drawRect(left,      bottom,     left + 1,     top + 1, new Color(82,  150, 38).getRGB()); //Left
		this.drawRect(left,      top + 1,    right,        top,     new Color(124, 221, 67).getRGB()); //Top
		this.drawRect(right - 1, bottom,     right,        top + 1, new Color(82,  150, 38).getRGB()); //Right
		this.drawRect(right - 1, bottom + 1, right,        bottom,  new Color(67,  125, 26).getRGB()); //RCorner
		this.drawRect(left + 1,  bottom,     right - 1,    top + 1, new Color(95,  170, 48).getRGB()); //Fill
		
		this.drawCenteredString(font, health, (right + left)/2, this.height - 24, Color.WHITE.getRGB());
	}
	
	private void renderAbilities() {
		Loadout loadout = PlayerData.get(Minecraft.getMinecraft().thePlayer).getLoadout();
		if (loadout == null) return;
		this.renderAbility(loadout.getUtility(), this.width/2 + 128, this.height - 24, 12);
		//this.renderAbility(loadout.getSpecial(), this.width/2 + 152, this.height - 24, 12); // TODO: CAUSING ISSUES
	}
	
	/**
	 * Renders an ability onto the screen.
	 * 
	 * @param abilityClass The ability to render
	 * @param x The x-coordinate on the screen to render it on. 
	 * @param y The y-coordinate on the screen to render it on.
	 * @param s The scale to render it at; The larger the scale the smaller the texture. 
	 */
	private void renderAbility(Class<? extends Ability> abilityClass, int x, int y, double s) {
		try {
			Ability ability = abilityClass.newInstance();
			ResourceLocation texture = ability.getTexture();
			
			GL11.glPushMatrix();
			GL11.glColor3f(1, 1, 1);
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			GL11.glScaled(1d/s, 1d/s, 1d/s);
			this.drawTexturedModalRect((int) (x * s), (int) (y * s), 0, 0, 256, 256);
			GL11.glPopMatrix();
		} 
		
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Renders the player's buffs onto the screen.
	 */
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
	
	/**
	 * Renders the player's crosshair onto the screen.
	 */
	private void renderCrosshair() {
		Optional<Survivor> survivor = Survivor.fromPlayer(Minecraft.getMinecraft().thePlayer);
		if (survivor.isPresent()) survivor.get().renderCrosshair(this);
		else GuiIngameForge.renderCrosshairs = true;
	}
}
