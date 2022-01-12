package znick_.riskofrain2.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import scala.actors.threadpool.Arrays;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;

public class RiskOfRain2Gui extends Gui {

	public RiskOfRain2Gui() {
		if (Minecraft.getMinecraft().thePlayer == null) return;
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
}
