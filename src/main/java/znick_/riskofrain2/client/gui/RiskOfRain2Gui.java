package znick_.riskofrain2.client.gui;

import java.awt.Color;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.client.GuiIngameForge;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.artifact.Artifact;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.api.ror.buff.stat.EntityStat;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.AbilityType;
import znick_.riskofrain2.api.ror.survivor.ability.Loadout;
import znick_.riskofrain2.client.render.RenderHelper;

public class RiskOfRain2Gui extends Gui {

	private final int width;
	private final int height;
	
	public RiskOfRain2Gui() {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		this.width = res.getScaledWidth();
		this.height = res.getScaledHeight();
		
		if (Minecraft.getMinecraft().thePlayer == null) return;
		
		this.renderAbilities();
		this.renderBuffs();
		this.renderArtifacts();
		this.renderCrosshair();
		this.renderHealth();
	}
	
	/**
	 * Renders the players enabled artifacts onto the screen.
	 */
	private void renderArtifacts() {
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		int i = 0;
		for (Artifact artifact : PlayerData.get(Minecraft.getMinecraft().thePlayer).getEnabledArtifacts()) {
			GL11.glPushMatrix();
			Minecraft.getMinecraft().getTextureManager().bindTexture(artifact.getTexture());
			GL11.glColor3f(1, 1, 1);
			
			RenderHelper.drawRect2D(this.width - (30 + (i * 26)), 4, 26, 26);
			i++;
			GL11.glPopMatrix();
		}
	}
	
	/**
	 * Renders the custom Risk of Rain 2 health bar onto the screen.
	 */
	private void renderHealth() {
		if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) return;
		
		GuiIngameForge.renderHealth = false;
		GuiIngameForge.renderExperiance = false;
			
		AbstractEntityData player = AbstractEntityData.get(Minecraft.getMinecraft().thePlayer);
		FontRenderer font = Minecraft.getMinecraft().fontRenderer;
		String health = (int) (player.getHealth() + player.getStat(EntityStat.BARRIER) + player.getStat(EntityStat.SHIELD))  + "/" + (int) player.getMaxHealth();
		
		int width = this.width/4;
		int left = this.width/20;
		int top = this.height - 26;
		int bottom = this.height - 16;
		
		int healthRight =  (int) (left + width * (player.getHealth()  / player.getMaxHealth()));
		int barrierRight = (int) (left + width * (player.getStat(EntityStat.BARRIER) / (player.getMaxHealth())));
		
		int shieldWidth = (int) (player.getStat(EntityStat.SHIELD) / player.getMaxHealth() * width);
		int shieldLeft = left + width - shieldWidth;
		
		this.drawRect(left,            bottom + 1, left + width,       top,     new Color(50,  50,  50).getRGB()); //Base
		this.drawRect(left,            bottom + 1, left + 1,           bottom,  new Color(67,  125, 26).getRGB()); //LCorner
		this.drawRect(left + 1,        bottom + 1, healthRight,        bottom,  new Color(82,  150, 38).getRGB()); //Bottom
		this.drawRect(left,            bottom,     left + 1,           top + 1, new Color(82,  150, 38).getRGB()); //Left
		this.drawRect(left,            top + 1,    healthRight,        top,     new Color(124, 221, 67).getRGB()); //Top
		this.drawRect(healthRight - 1, bottom,     healthRight,        top + 1, new Color(82,  150, 38).getRGB()); //Right
		this.drawRect(healthRight - 1, bottom + 1, healthRight,        bottom,  new Color(67,  125, 26).getRGB()); //RCorner
		this.drawRect(left + 1,        bottom,     healthRight - 1,    top + 1, new Color(95,  170, 48).getRGB()); //Fill
		
		// Render shield
		if (player.getStat(EntityStat.SHIELD) > 0) {
			this.drawRect(shieldLeft, top + 1,    left + width, top,    new Color(77, 105, 185).getRGB()); // Top Shield
			this.drawRect(shieldLeft, bottom + 1, left + width, bottom, new Color(44, 64, 117).getRGB()); // Bottom Shield
			this.drawRect(shieldLeft, top + 1,    left + width, bottom, new Color(50, 76, 147).getRGB()); // Fill shield
		}
		
		// Render Barrier Overlay
		if (player.getStat(EntityStat.BARRIER) > 0) {
			int barrierBorder = new Color(242, 218, 104).getRGB();
		
			this.drawRect(left + 1,         bottom + 1, barrierRight, bottom,  barrierBorder); //Bottom Barrier
			this.drawRect(left,             bottom + 1, left + 1,     top + 1, barrierBorder); //Left Barrier
			this.drawRect(left,             top + 1,    barrierRight, top,     barrierBorder); //Top Barrier
			this.drawRect(barrierRight - 1, bottom,     barrierRight, top + 1, barrierBorder); //Right Barrier
		
			this.drawGradientRect(left + 1, top + 1, barrierRight - 1, bottom, new Color(231, 180, 61).getRGB(), new Color(159, 125, 7).getRGB()); //Barrier Gradient Fill
		}
		
		this.drawCenteredString(font, health, (left + width + left)/2, this.height - 24, Color.WHITE.getRGB());
	}
	
	private void renderAbilities() {
		Loadout loadout = AbstractEntityData.get(Minecraft.getMinecraft().thePlayer).getLoadout();
		if (loadout == null) return;
		int i = 0;
		for (AbilityType type : AbilityType.values()) {
			if (type == AbilityType.EQUIPMENT) continue;
			this.renderAbility(loadout.getAbility(type), this.width/2 + 128 + 24 * i, this.height - 24);
			i++;
		}
	}
	
	/**
	 * Renders an ability onto the screen.
	 * 
	 * @param abilityClass The ability to render
	 * @param x The x-coordinate on the screen to render it on. 
	 * @param y The y-coordinate on the screen to render it on.
	 * @param s The scale to render it at; The larger the scale the smaller the texture. 
	 */
	private void renderAbility(Ability ability, int x, int y) {
		GL11.glPushMatrix();
		GL11.glColor3f(1, 1, 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ability.getTexture());
		RenderHelper.drawRect2D(x, y, 21, 21);
		GL11.glPopMatrix();
	}
	
	/**
	 * Renders the player's buffs onto the screen.
	 */
	private void renderBuffs() {
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		Map<Class<? extends Buff>, Map.Entry<Integer, Integer>> renderedBuffs = new LinkedHashMap<>();
		int i = 0;
		
		for (Buff buff : AbstractEntityData.get(Minecraft.getMinecraft().thePlayer).getBuffs()) {
			
			// If the buff has no texture, just skip it.
			if (buff.getIconTexture() == null) continue;
			
			// Check if a player already has a stack of it
			if (renderedBuffs.containsKey(buff.getClass())) {
				Map.Entry<Integer, Integer> buffEntry = renderedBuffs.get(buff.getClass());
				buffEntry.setValue(buffEntry.getValue() + 1);
				continue;
			}
			
			// Otherwise, render the buff icon normally 
			GL11.glPushMatrix();
			Minecraft.getMinecraft().getTextureManager().bindTexture(buff.getIconTexture());
			GL11.glColor3f(1, 1, 1);
			
			RenderHelper.drawRect2D(4 + (i * 26), 4, 26, 26);
			renderedBuffs.put(buff.getClass(), new AbstractMap.SimpleEntry<>(4 + (i * 26), 1));
			i++;
			GL11.glPopMatrix();
		}
		
		for (Map.Entry<Integer, Integer> buffEntry : renderedBuffs.values()) {
			if (buffEntry.getValue() > 1) this.drawString(Minecraft.getMinecraft().fontRenderer, "x" + buffEntry.getValue(), buffEntry.getKey() + 15, 24, Color.WHITE.getRGB());
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
