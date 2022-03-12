package znick_.riskofrain2.client.gui.logbook;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.client.gui.GuiHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class LogbookGui extends GuiScreen {

	public static final int GUI_ID = GuiHandler.getNextID(LogbookGui.class);
	private static final int BUTTON_SCALE = 10;

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
		this.mc.getTextureManager().bindTexture(new ResourceLocation(RiskOfRain2.MODID + ":textures/gui/logbook/background.png"));
		int d = 256;
		int s = 3;
		int i = 0;
		int j = 0;
		GL11.glScaled(1d / s, 1d / s, 1d / s);
		while (true) {
			this.drawTexturedModalRect(i, j, 0, 0, d, d);
			i += d;
			if (i > this.width * s) {
				i = 0;
				j += d;
			}
			if (j > this.height * s) break;
		}
		GL11.glPopMatrix();
	}

	private void addItemButtons() {
		int d = 256;
		int x = 0;
		int y = 0;
		int i = 0;
		int j = 0;
		for (RiskOfRain2Item item : RiskOfRain2Items.ITEM_SET) {
			if (item.isSpecial()) continue;
			this.buttonList.add(new ItemButton(j++, item, x, y, 1d / BUTTON_SCALE));
			x += d + 25;
			i++;
			if (i > 13) {
				i = 0;
				x = 0;
				y += d + 25;
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
