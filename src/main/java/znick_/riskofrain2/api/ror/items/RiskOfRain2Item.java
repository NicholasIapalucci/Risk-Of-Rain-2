package znick_.riskofrain2.api.ror.items;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2CreativeTabs;

public abstract class RiskOfRain2Item extends Item {

	private final String name;

	protected RiskOfRain2Item(String name) {
		this.name = name;
		this.setTextureName(RiskOfRain2.MODID + ":items/" + name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(RiskOfRain2CreativeTabs.ITEMS);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someParam) {

		//Clear the info and add the item name
		info.clear();
		info.add(this.getRarity().getColor() + this.getProperName());

		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			info.clear();
			info.add(this.getRarity().getColor() + this.getProperName());
			info.add(EnumChatFormatting.DARK_GRAY + "Hold shift for info...");
		} else {
			info.clear();
			info.add(this.getRarity().getColor() + this.getProperName());
			for (int i = 0; i < this.getSplicedDesc().size(); i++) {
				info.add(EnumChatFormatting.GRAY + this.getSplicedDesc().get(i));
			}
			info.add("");
			String rar = Character.toString(this.getRarity().toString().charAt(0)).toUpperCase() + this.getRarity().toString().substring(1).toLowerCase();
			String cat = Character.toString(this.getCategory().toString().charAt(0)).toUpperCase() + this.getCategory().toString().substring(1).toLowerCase();
			info.add("Rarity: " + this.getRarity().getColor() + rar);
			info.add("Category: " + this.getCategory().getColor() + cat);
		}
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		switch(this.getRarity()) {
		case GREEN: return EnumRarity.rare;
		case RED: return EnumRarity.epic;
		case BOSS: return EnumRarity.uncommon;
		default: return EnumRarity.common;
		}
	}

	/**
	 * Returns the description of this item broken up into lines of
	 * acceptable size. This is the method that is called to add the information to
	 * the item in the inventory GUI, so if an item needs a custom description it should
	 * override this method instead of {@link #getDescription()}.
	 */
	protected List<String> getSplicedDesc() {
		String localDesc = this.getDescription();
		List<String> splicedDesc = new ArrayList<String>();

		for (int i = 25; i > 0; i--) {
			if (localDesc.length() > 25) {
				if (localDesc.charAt(i) == ' ') {
					splicedDesc.add(localDesc.substring(0, i + 1));
					localDesc = localDesc.substring(i + 1, localDesc.length());
				}
			} else {
				splicedDesc.add(localDesc);
				break;
			}
		}

		return splicedDesc;
	}

	public String getProperName() {
		String pn = "";
		for (int i = 0; i < this.name.length(); i++) {
			if (i == 0) pn += Character.toString(this.name.charAt(i)).toUpperCase();
			else if (this.name.charAt(i) == '_') pn += " ";
			else if (this.name.charAt(i - 1) == '_') pn += Character.toString(this.name.charAt(i)).toUpperCase();
			else pn += this.name.charAt(i);
		}
		return pn;
	}

	public abstract ItemCategory getCategory();
	public abstract ItemRarity getRarity();
	public abstract String getDescription();
	
	public String getTextureName() {
		return this.getIconString();
	}
}
