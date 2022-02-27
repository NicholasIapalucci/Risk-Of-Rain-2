package znick_.riskofrain2.client.gui.menu;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import cpw.mods.fml.client.GuiModList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

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
		//this.drawCursor(x, y);
	}
	
	/**
	 * Hides the default cursor and draws the custom Risk of Rain 2 cursor over it.
	 * 
	 * @param mouseX The x-position of the cursor
	 * @param mouseY The y-position of the cursor
	 */
	private void drawCursor(int mouseX, int mouseY) {
		Mouse.setGrabbed(true);
		GL11.glPushMatrix();
		double s = 0.4;
		double s1 = 1d/s;
		GL11.glScaled(s, s, s);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("ror2:textures/gui/menu/cursor.png"));
		this.drawTexturedModalRect((int) (mouseX * s1), (int) (mouseY * s1), 0, 0, 256, 256);
		GL11.glPopMatrix();
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
        for (k = 0; k < this.labelList.size();  ++k) ((GuiLabel)  this.labelList.get(k)).func_146159_a(this.mc, x, y);
	}
	
	/**
	 * Renders the Minecraft title logo onto the top of the screen.
	 */
	private void renderMinecraftTitle() { //TODO: Refactor for less math + better performance
		int f = this.width/2 - 137;
		this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/title/minecraft.png"));
		GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(f + 0, 30, 0, 0, 155, 44);
        this.drawTexturedModalRect(f + 155, 30, 0, 45, 155, 44);
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
		case 3: this.mc.displayGuiScreen(new GuiModList(this)); return;
		case 4: new RealmsBridge().switchToRealms(this); return;
		case 5: this.mc.shutdown(); return;
		case 6: this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager())); return;
		}
	}

	/**
	 * Draws a textured rectangle that can be bigger than the default limit of 256x256. Used for rendering
	 * the background image. 
	 * 
	 * @param x The x-coordinate of the upper left rectangle corner
	 * @param y The y-coordinate of the upper-left rectangle corner
	 * @param width The width of the rectangle to render
	 * @param height The height of the rectangle to render
	 * @param textureWidth The width of the rectangle texture
	 * @param textureHeight The height of the rectangle texture
	 */
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
	public void onGuiClosed() {
		Mouse.setGrabbed(false);
	}
}
