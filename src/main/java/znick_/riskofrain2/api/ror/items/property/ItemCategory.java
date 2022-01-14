package znick_.riskofrain2.api.ror.items.property;

import net.minecraft.util.EnumChatFormatting;

public enum ItemCategory {
	DAMAGE(EnumChatFormatting.RED), 
	HEALING(EnumChatFormatting.GREEN), 
	UTILITY(EnumChatFormatting.LIGHT_PURPLE), 
	ACTIVE(EnumChatFormatting.GOLD),
	UNKNOWN(EnumChatFormatting.GRAY);

	private EnumChatFormatting color;

	private ItemCategory(EnumChatFormatting color) {
		this.color = color;
	}

	public EnumChatFormatting getColor() {
		return this.color;
	}
}
