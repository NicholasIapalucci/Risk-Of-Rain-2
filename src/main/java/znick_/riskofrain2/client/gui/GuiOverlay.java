package znick_.riskofrain2.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.survivor.Survivor;

public class GuiOverlay extends Gui {
	
	private int width;
	private int height;
	
	private Minecraft mc;
	private Survivor character;
	
	private EntityPlayer player;
	private PlayerData extendedPlayer;
	
	public GuiOverlay(Minecraft mc, EntityPlayer player, Survivor character) {
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		
		this.width = scaled.getScaledWidth();
		this.height = scaled.getScaledHeight();
		this.mc = mc;
		this.character = character;
		
		if (player != null) {
			this.player = player;
			this.extendedPlayer = PlayerData.get(player);
			this.drawMoney();
		} else {
			this.drawBlankMoney();
		}
		
		this.drawUtilityIcon();
		this.drawSpecialIcon();
	}
	
	private void drawMoney() {
		//this.drawString(mc.fontRenderer, "$" + Integer.toString(this.extendedPlayer.getMoney()), 10, 10, Integer.parseInt("FFF519", 16));
		//this.drawString(mc.fontRenderer, "$" + Integer.toString(this.extendedPlayer.getLunarCoins()), 10, 20, Integer.parseInt("E2BCFF", 16));
	}
	
	private void drawBlankMoney() {
		this.drawString(mc.fontRenderer, "$0", 10, 10, Integer.parseInt("FFF519", 16));
		this.drawString(mc.fontRenderer, "$0", 10, 20, Integer.parseInt("E2BCFF", 16));
	}
	
	private void drawUtilityIcon() {
//		ResourceLocation utility = new ResourceLocation(RiskOfRain2.MODID + ":textures/gui/" + character.getName().toLowerCase() +"/utility.png");
//		mc.getTextureManager().bindTexture(utility);
//		GL11.glColor3f(1, 1, 1);
//		this.drawTexturedModalRect(width - 59, height - 20, 0, 0, 16, 16);
//		
//		if (!this.wrappedUtility.isReady()) {
//			mc.getTextureManager().bindTexture(new ResourceLocation(RiskOfRain2.MODID + ":textures/gui/cooldown_overlay.png"));
//			this.drawTexturedModalRect(width - 59, height - 20, 0, 0, 16, 16);
//			drawCenteredString(mc.fontRenderer, Integer.toString(this.wrappedUtility.getCooldown()), width - 50, height - 15, Integer.parseInt("FF5555", 16));
//		}
//		
//		if (this.wrappedUtility.getCharges() > 1) {
//			drawCenteredString(mc.fontRenderer, Integer.toString(this.wrappedUtility.getCharges()), width - 50, height - 28, Integer.parseInt("FFFFFF", 16));
//		}
	}
	
	private void drawSpecialIcon() {
//		ResourceLocation spec = new ResourceLocation(RiskOfRain2.MODID + ":textures/gui/" + character.getName().toLowerCase() +"/special.png");
//		mc.getTextureManager().bindTexture(spec);
//		GL11.glColor3f(1, 1, 1);
//		this.drawTexturedModalRect(width - 41, height - 20, 0, 0, 16, 16);
//		
//		if (!this.wrappedSpecial.isReady()) {
//			mc.getTextureManager().bindTexture(new ResourceLocation(RiskOfRain2.MODID + ":textures/gui/cooldown_overlay.png"));
//			this.drawTexturedModalRect(width - 41, height - 20, 0, 0, 16, 16);
//			drawCenteredString(mc.fontRenderer, Integer.toString(this.wrappedSpecial.getCooldown()), width - 32, height - 15, Integer.parseInt("FF5555", 16));
//		}
//		
//		if (this.wrappedSpecial.getCharges() > 1) {
//			drawCenteredString(mc.fontRenderer, Integer.toString(this.wrappedSpecial.getCharges()), width - 50, height - 28, Integer.parseInt("FFFFFF", 16));
//		}
	}
	
}
