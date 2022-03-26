package znick_.riskofrain2.client.gui.logbook;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.client.gui.GuiHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class LogbookGui extends GuiScreen {

	public static final int GUI_ID = GuiHandler.getNextID(LogbookGui.class);
	private static final int BUTTON_SCALE = 10;
	
	public LogbookGui(int x, int y, int z) {
		
	}
	
	@Override
	public void initGui() {
		this.addItemButtons();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		this.drawBackground();
		super.drawScreen(mouseX, mouseY, partialTick);
	}

	private void drawBackground() {
		GL11.glPushMatrix();
		this.mc.getTextureManager().bindTexture(RiskOfRain2Resources.get(RiskOfRain2Mod.MODID + ":textures/gui/logbook/background.png"));
		int d = 256;
		int s = 3;
		int i = 0;
		int j = 0;
		GL11.glScaled(1d / s, 1d / s, 1d / s);
		while (j <= this.height * s) {
			this.drawTexturedModalRect(i, j, 0, 0, d, d);
			i += d;
			if (i > this.width * s) {
				i = 0;
				j += d;
			}
		}
		GL11.glPopMatrix();
	}

	private void addItemButtons() {
		int d = 32;
		int x = 0;
		int y = 0;
		int i = 0;
		int j = 0;
		for (RiskOfRain2Item item : RiskOfRain2Items.ITEM_SET) {
			if (item.isSpecial()) continue;
			this.buttonList.add(new ItemButton(j++, item, x, y, d, d));
			x += d + 3;
			i++;
			if (i > 13) {
				i = 0;
				x = 0;
				y += d + 3;
			}
		}
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if (button instanceof ItemButton) {
			ItemButton itemButton = (ItemButton) button;
			System.out.println(itemButton.getItem());
		}
	}
}
