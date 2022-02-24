package znick_.riskofrain2.client.gui.menu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;

public class RiskOfRain2MenuButton extends TexturedGuiButton {

	private boolean playedSound;
	
	public RiskOfRain2MenuButton(int id, int x, int y, int width, int height, double scale, String label) {
		super(id, x, y, width, height, scale, "ror2:textures/gui/menu/" + label + ".png");
	}
	
	@Override
	public void func_146113_a(SoundHandler sound) {
        sound.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("ror2", "button_press"), 1.0F));
    }
	
	@Override
	public void drawButton(Minecraft mc, int x, int y) {
		super.drawButton(mc, x, y);
		
		if (this.getHoverState(this.field_146123_n) == 2) {
			if (!this.playedSound) {
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("ror2", "button_hover"), 1.0F));
				this.playedSound = true;
			}
		} 
		
		else {
			this.playedSound = false;
		}
	}
}
