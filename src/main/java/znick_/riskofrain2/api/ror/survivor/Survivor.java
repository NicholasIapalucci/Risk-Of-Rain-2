package znick_.riskofrain2.api.ror.survivor;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
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
	
	private static final Map<Integer, Survivor> SURVIVOR_IDS = new HashMap<>();
	
	public static final Huntress HUNTRESS = new Huntress();
	
	private final Map<AbilityType, Set<Ability>> abilities = new HashMap<>();
	private final int id;
	
	protected Survivor() {
		this.id = SURVIVOR_IDS.size() + 1;
		SURVIVOR_IDS.put(this.id, this);
		for (AbilityType type : AbilityType.values()) abilities.put(type, new LinkedHashSet<>());
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
	
	public int getUniqueID() {
		return this.id;
	}
	
	public void addAbility(Ability ability) {
		this.abilities.get(ability.getAbilityType()).add(ability);
	}
	
	public static Survivor[] getSurvivors() {
		return SURVIVOR_IDS.values().toArray(new Survivor[0]);
	}
	
	public static Optional<Survivor> fromPlayer(EntityPlayer player) {
		for (Survivor survivor : Survivor.getSurvivors()) {
			if (survivor.isPlayer(player)) return Optional.of(survivor);
		}
		return Optional.empty();
	}

	public static Survivor fromID(int id) {
		return SURVIVOR_IDS.get(id);
	}
	
}
