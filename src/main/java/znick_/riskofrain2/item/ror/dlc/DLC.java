package znick_.riskofrain2.item.ror.dlc;

import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.util.helper.StringHelper;

/**
 * The {@code DLC} enum represents a list of downloadable content packs in the Risk of Rain 2
 * game. 
 * 
 * @author zNick_
 */
public enum DLC {
	BASE_GAME(null),
	SURVIVORS_OF_THE_VOID(EnumChatFormatting.LIGHT_PURPLE);

	private final EnumChatFormatting color;
	
	private DLC(EnumChatFormatting color) {
		this.color = color;
	}
	
	public EnumChatFormatting getColor() {
		return this.color;
	}
	
	@Override
	public String toString() {
		return StringHelper.format(this.name());
	}
}
