package znick_.riskofrain2.api.ror.survivor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.AbilityType;
import znick_.riskofrain2.api.ror.survivor.ability.Loadout;
import znick_.riskofrain2.api.ror.survivor.huntress.Huntress;
import znick_.riskofrain2.item.armor.ArmorType;

public abstract class Survivor {
	
	private static final Set<Survivor> SURVIVORS = new HashSet<>();

	public static final Huntress HUNTRESS = new Huntress();
	
	private final Map<AbilityType, Set<Ability>> abilities = new HashMap<>();
	
	protected Survivor() {
		SURVIVORS.add(this);
		for (AbilityType type : AbilityType.values()) {
			abilities.put(type, new LinkedHashSet<>());
		}
	}
	
	public abstract Loadout getDefaultLoadout();
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
	
	public void renderCrosshair(Gui gui) {}
	
	public void addAbility(Ability ability) {
		this.abilities.get(ability.getAbilityType()).add(ability);
	}
	
	public static Survivor[] getSurvivors() {
		return SURVIVORS.toArray(new Survivor[0]);
	}
	
}
