package znick_.riskofrain2.api.ror.survivor.huntress;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Loadout;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.BlinkAbility;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.ArrowRainAbility;
import znick_.riskofrain2.client.gui.Crosshair;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.armor.ArmorType;

public class Huntress extends Survivor {
	
	private static final Crosshair DOT_CROSSHAIR = new Crosshair("huntress/crosshairs/dot.png", 0.02);
	private static final Crosshair ARROW_CROSSHAIR = new Crosshair("huntress/crosshairs/arrow.png", 0.04);
	
	public Huntress() {
		
	}
	
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
	public void renderCrosshair(Gui gui) {
		if (Minecraft.getMinecraft().thePlayer.isSprinting()) ARROW_CROSSHAIR.render(gui);
		else DOT_CROSSHAIR.render(gui);
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

	@Override
	public Loadout getDefaultLoadout() {
		return new Loadout(null, null, BlinkAbility.class, ArrowRainAbility.class);
	}
}
