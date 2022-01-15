package znick_.riskofrain2.api.ror.survivor.huntress;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.api.ror.ability.SpecialAbility;
import znick_.riskofrain2.api.ror.ability.UtilityAbility;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.armor.ArmorType;

public class Huntress extends Survivor {

	public static final UtilityAbility BLINK = new UtilityAbility("blink", 140);
	public static final SpecialAbility ARROW_RAIN = new SpecialAbility("arrow_rain", 240);
	
	@Override
	public ArmorMaterial getArmorMaterial() {
		return ItemArmor.ArmorMaterial.DIAMOND; //TODO: Update armor material
	}
	
	@Override
	public String getName() {
		return "Huntress";
	}
	
	@Override
	public EnumChatFormatting getColor() {
		return EnumChatFormatting.RED;
	}
	
	@Override
	public Item getArmorPiece(ArmorType armorType) {
		switch(armorType) {
		case HELMET: return RiskOfRain2Items.HUNTRESS_HELMET;
		case CHESTPLATE: return RiskOfRain2Items.HUNTRESS_CHESTPLATE;
		case LEGGINGS: return RiskOfRain2Items.HUNTRESS_LEGGINGS;
		case BOOTS: return RiskOfRain2Items.HUNTESS_BOOTS;
		default: throw new IllegalArgumentException("There is no ArmorType " + armorType.getName());
		}
	}
}
