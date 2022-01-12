package znick_.riskofrain2.api.ror.character;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.api.ror.character.huntress.Huntress;
import znick_.riskofrain2.item.armor.ArmorType;

public abstract class PlayableCharacter {
	
	public static final Huntress HUNTRESS = new Huntress();
	
	public abstract ItemArmor.ArmorMaterial getArmorMaterial();
	public abstract String getName();
	public abstract EnumChatFormatting getColor();
	public abstract Item getArmorPiece(ArmorType armorType);
	
	public static boolean isPlayerSurvivor(EntityPlayer player, PlayableCharacter character) {
		if (player != null ) {
			if (player.getCurrentArmor(0) != null && player.getCurrentArmor(1) != null && player.getCurrentArmor(2) != null && player.getCurrentArmor(3) != null) {
				if (player.getCurrentArmor(3).getItem() != character.getArmorPiece(ArmorType.HELMET)) return false;
				if (player.getCurrentArmor(2).getItem() != character.getArmorPiece(ArmorType.CHESTPLATE)) return false;
				if (player.getCurrentArmor(1).getItem() != character.getArmorPiece(ArmorType.LEGGINGS)) return false;
				if (player.getCurrentArmor(0).getItem() != character.getArmorPiece(ArmorType.BOOTS)) return false;
				return true;
			}
		}
		
		return false;
	}
	
}
