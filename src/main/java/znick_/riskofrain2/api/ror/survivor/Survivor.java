package znick_.riskofrain2.api.ror.survivor;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.api.ror.survivor.huntress.Huntress;
import znick_.riskofrain2.item.armor.ArmorType;

public abstract class Survivor {
	
	private static final Set<Survivor> SURVIVORS = new HashSet<>();
	
	public static final Huntress HUNTRESS = new Huntress();
	
	protected Survivor() {
		SURVIVORS.add(this);
	}
	
	public abstract ItemArmor.ArmorMaterial getArmorMaterial();
	public abstract String getName();
	public abstract EnumChatFormatting getColor();
	public abstract Item getArmorPiece(ArmorType armorType);
	
	public boolean isPlayer(EntityPlayer player) {
		for (int i = 0; i < 4; i++) if (player.getCurrentArmor(i) == null) return false;
		if (player.getCurrentArmor(3).getItem() != this.getArmorPiece(ArmorType.HELMET)) return false;
		if (player.getCurrentArmor(2).getItem() != this.getArmorPiece(ArmorType.CHESTPLATE)) return false;
		if (player.getCurrentArmor(1).getItem() != this.getArmorPiece(ArmorType.LEGGINGS)) return false;
		if (player.getCurrentArmor(0).getItem() != this.getArmorPiece(ArmorType.BOOTS)) return false;
		return true;
	}
	
	public static Survivor[] getSurvivors() {
		return SURVIVORS.toArray(new Survivor[0]);
	}
	
}
