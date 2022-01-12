package znick_.riskofrain2.item.ror.property;

import net.minecraft.util.EnumChatFormatting;

public enum ItemRarity {
	
	WHITE(EnumChatFormatting.WHITE),
	GREEN(EnumChatFormatting.GREEN),
	RED(EnumChatFormatting.RED),
	BOSS(EnumChatFormatting.YELLOW),
	LUNAR(EnumChatFormatting.BLUE),
	ACTIVE(EnumChatFormatting.GOLD),
	UNKNOWN(EnumChatFormatting.GRAY);
	
	private EnumChatFormatting color;
	
	private ItemRarity(EnumChatFormatting color) {
		this.color = color;
	}
	
	public EnumChatFormatting getColor() {
		return this.color;
	}
}
