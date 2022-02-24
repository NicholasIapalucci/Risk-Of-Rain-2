package znick_.riskofrain2.client.gui.menu;

import java.io.BufferedReader;
import java.net.URI;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import cpw.mods.fml.client.GuiModList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * The Risk of Rain 2 mod's custom main menu screen.
 * 
 * @author zNick_
 */
public class RiskOfRain2MainMenu extends GuiMainMenu {

	private final Object field_104025_t = new Object();
	private String field_92025_p;
	private String field_146972_A;
	private String field_104024_v;
	public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
	private int field_92024_r;
	private int field_92023_s;
	private int field_92022_t;
	private int field_92021_u;
	private int field_92020_v;
	private int field_92019_w;
	private ResourceLocation field_110351_G;
	private GuiButton selectedButton;

	public RiskOfRain2MainMenu() {
		this.field_146972_A = field_96138_a;
		this.field_92025_p = "";

		if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.func_153193_b()) {
			this.field_92025_p = I18n.format("title.oldgl1", new Object[0]);
			this.field_146972_A = I18n.format("title.oldgl2", new Object[0]);
			this.field_104024_v = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
		}
	}

	@Override
	public void initGui() {
		this.addButtons();

		synchronized (this.field_104025_t) {
			this.field_92023_s = this.fontRendererObj.getStringWidth(this.field_92025_p);
			this.field_92024_r = this.fontRendererObj.getStringWidth(this.field_146972_A);
			int j = Math.max(this.field_92023_s, this.field_92024_r);
			this.field_92022_t = (this.width - j) / 2;
			this.field_92021_u = ((GuiButton) this.buttonList.get(0)).yPosition - 24;
			this.field_92020_v = this.field_92022_t + j;
			this.field_92019_w = this.field_92021_u + 24;
		}
	}
	
	@Override
	public void drawScreen(int x, int y, float partialTick) {
		this.renderBackground();
		this.renderMinecraftTitle();
		this.renderButtons(x, y);
	}
	
	/**
	 * Renders the Risk Of Rain 2 background image onto the menu screen.
	 */
	private void renderBackground() {
		this.mc.getTextureManager().bindTexture(new ResourceLocation("ror2:textures/gui/menu/background.png"));
		this.drawTexturedRect(0, 0, 1920, 1080, this.width, this.height);
	}
	
	/**
	 * Renders the buttons onto the screen.
	 * 
	 * @param x The mouse x-coordinate
	 * @param y The mouse y-coordinate
	 */
	private void renderButtons(int x, int y) {
		int k;
        for (k = 0; k < this.buttonList.size(); ++k) ((GuiButton) this.buttonList.get(k)).drawButton(this.mc, x, y);
        for (k = 0; k < this.labelList.size();  ++k) ((GuiLabel) this.labelList.get(k)).func_146159_a(this.mc, x, y);
	}
	
	/**
	 * Renders the Minecraft title logo onto the top of the screen.
	 */
	private void renderMinecraftTitle() {
		short short1 = 274;
		int f = this.width / 2 - short1 / 2;
        byte b0 = 30;
		this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/title/minecraft.png"));
		GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(f + 0, b0 + 0, 0, 0, 155, 44);
        this.drawTexturedModalRect(f + 155, b0 + 0, 0, 45, 155, 44);
	}

	/**
	 * Adds the buttons to the button list.
	 */
	private void addButtons() {
		this.buttonList.add(new RiskOfRain2MenuButton(0, 20, this.height - 156, 256, 36, 0.5, "singleplayer"));
		this.buttonList.add(new RiskOfRain2MenuButton(1, 20, this.height - 136, 256, 36, 0.5, "multiplayer"));
		this.buttonList.add(new RiskOfRain2MenuButton(2, 20, this.height - 116, 256, 36, 0.5, "options"));
		this.buttonList.add(new RiskOfRain2MenuButton(3, 20, this.height - 96, 256, 36, 0.5, "mods"));
		this.buttonList.add(new RiskOfRain2MenuButton(4, 20, this.height - 76, 256, 36, 0.5, "realms"));
		this.buttonList.add(new RiskOfRain2MenuButton(5, 20, this.height - 56, 256, 36, 0.5, "quit"));
	}

	/**
	 * Called when a {@code GuiButton} is pressed. Used to open further GUIs such as the singleplayer or
	 * multiplayer menu.
	 * 
	 * @param button The {@code GuiButton} that was pressed
	 */
	@Override
	protected void actionPerformed(GuiButton button) {
		switch(button.id) {
		case 0: this.mc.displayGuiScreen(new GuiSelectWorld(this)); return;
		case 1: this.mc.displayGuiScreen(new GuiMultiplayer(this)); return;
		case 2: this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings)); return;
		case 3: this.mc.displayGuiScreen(new GuiModList(this));
		case 4: new RealmsBridge().switchToRealms(this); return;
		case 5: this.mc.shutdown(); return;
		case 6: this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager())); return;
		}
	}

	private void drawTexturedRect(int x, int y, int width, int height, int textureWidth, int textureHeight) {
		int u = 0;
		int v = 0;
		float f = 1F / (float) textureWidth;
		float f1 = 1F / (float) textureHeight;

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();

		tessellator.addVertexWithUV((double) (x),         (double) (y + height), 0, (double) ((float) (u) * f),         (double) ((float) (v + height) * f1));
		tessellator.addVertexWithUV((double) (x + width), (double) (y + height), 0, (double) ((float) (u + width) * f), (double) ((float) (v + height) * f1));
		tessellator.addVertexWithUV((double) (x + width), (double) (y),          0, (double) ((float) (u + width) * f), (double) ((float) (v) * f1));
		tessellator.addVertexWithUV((double) (x),         (double) (y),          0, (double) ((float) (u) * f),         (double) ((float) (v) * f1));

		tessellator.draw();
	}

	@Override
	protected void mouseClicked(int x, int y, int state) {
		if (state == 0) {
			for (int l = 0; l < this.buttonList.size(); ++l) {
				GuiButton guibutton = (GuiButton) this.buttonList.get(l);

				if (guibutton.mousePressed(this.mc, x, y)) {
					GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre(this, guibutton, this.buttonList);
					if (MinecraftForge.EVENT_BUS.post(event)) break;
					this.selectedButton = event.button;
					event.button.func_146113_a(this.mc.getSoundHandler());
					this.actionPerformed(event.button);
					if (this.equals(this.mc.currentScreen)) MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.ActionPerformedEvent.Post(this, event.button, this.buttonList));
				}
			}
		}

		Object object = this.field_104025_t;

		synchronized (this.field_104025_t) {
			if (this.field_92025_p.length() > 0 && x >= this.field_92022_t && x <= this.field_92020_v && y >= this.field_92021_u && y <= this.field_92019_w) {
				GuiConfirmOpenLink guiconfirmopenlink = new GuiConfirmOpenLink(this, this.field_104024_v, 13, true);
				guiconfirmopenlink.func_146358_g();
				this.mc.displayGuiScreen(guiconfirmopenlink);
			}
		}
	}

	@Override
	protected void mouseMovedOrUp(int x, int y, int state) {
		if (this.selectedButton != null && state == 0) {
			this.selectedButton.mouseReleased(x, y);
			this.selectedButton = null;
		}
	}
}
