package znick_.riskofrain2.item.ror.property;

import net.minecraft.util.EnumChatFormatting;

/**
 * The {@code ItemCategory} enum represents various classes that items are sorted into. These are used for
 * opening special chests such as damage or healing chests.
 * 
 * @author zNick_
 */
public enum ItemCategory {
	
	/**the {@code DAMAGE} item category that appear in red damage chests.*/
	DAMAGE(EnumChatFormatting.RED), 
	
	/**the {@code HEALING} item category that appear in green healing chests.*/
	HEALING(EnumChatFormatting.GREEN), 
	
	UTILITY(EnumChatFormatting.LIGHT_PURPLE), 
	EQUIPMENT(EnumChatFormatting.GOLD),
	UNKNOWN(EnumChatFormatting.GRAY);

	private EnumChatFormatting color;

	private ItemCategory(EnumChatFormatting color) {
		this.color = color;
	}

	public EnumChatFormatting getColor() {
		return this.color;
	}
}
