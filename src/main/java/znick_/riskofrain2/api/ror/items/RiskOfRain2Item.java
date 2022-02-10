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
import znick_.riskofrain2.api.mc.CustomRarity;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2CreativeTabs;

/**
 * Class used for creating items that are in the Risk of Rain 2 game. 
 * 
 * @author zNick_
 */
public abstract class RiskOfRain2Item extends Item {

	/**The name of the item.*/
	private final String name;

	/**
	 * Creates a new {@code RiskOfRain2Item} with the given name.
	 * 
	 * @param name The name of the item. The name is used as the unlocalized name to register the item into
	 * the game, the proper name to be rendered on the item, and the name of the location for the item's
	 * texture. The name should be in all lowercase and not include whitespaces, but rather underscores.
	 */
	protected RiskOfRain2Item(String name) {
		this.name = name;
		this.setTextureName(RiskOfRain2.MODID + ":items/" + name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(RiskOfRain2CreativeTabs.ITEMS);
	}

	/**
	 * Adds lines of information to the description of the item when hovering over it with the mouse in
	 * the inventory GUI. Specifies rarity, category, and description.
	 * 
	 * @param stack The item stack to hover over.
	 * @param player The player who has the item.
	 * @param info The tooltip list of strings to add to the description. Each string entry in the list
	 * is one line in the description.
	 * @param someParam honestly no idea about this one.
	 */
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someParam) {
		super.addInformation(stack, player, info, someParam);

		// Check if the shift key is NOT down
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			// Add the "shift for info" prompt
			info.add(EnumChatFormatting.DARK_GRAY + "Hold shift for info...");
		} 
		
		// Check if the shift key IS held down
		else {
			for (int i = 0; i < this.getSplicedDesc().size(); i++) {
				info.add(EnumChatFormatting.GRAY + this.getSplicedDesc().get(i));
			}
			// Add a blank line
			info.add("");

			// Add the rarity
			String rar = Character.toString(this.getRarity().toString().charAt(0)).toUpperCase() + this.getRarity().toString().substring(1).toLowerCase();
			info.add("Rarity: " + this.getRarity().getColor() + rar);
			
			// Add the category
			String cat = Character.toString(this.getCategory().toString().charAt(0)).toUpperCase() + this.getCategory().toString().substring(1).toLowerCase();
			info.add("Category: " + this.getCategory().getColor() + cat);
		}
	}
	
	/**
	 * Returns the rarity of this item.
	 * 
	 * @param stack The item stack to get the rarity of.
	 */
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		switch(this.getRarity()) {
		case GREEN: return CustomRarity.GREEN;
		case RED: return CustomRarity.RED;
		case LUNAR: return CustomRarity.LUNAR;
		case BOSS: return EnumRarity.uncommon;
		case ACTIVE: return CustomRarity.EQUIPMENT;
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
	
	/**Returns the category of this item.*/
	public abstract ItemCategory getCategory();
	
	/**Returns the rarity of this item.*/
	public abstract ItemRarity getRarity();
	
	/**
	 * Returns the description of this item. Descriptions should follow the Risk Of Rain 2 in-game descriptions
	 * exactly, unless there is a specific reason to not do so, such as if the item has different behavior in
	 * the mod than in the original game.
	 */
	public abstract String getDescription();
	
	/**
	 * Returns whether or not this is a special item that should not be included in the standard
	 * set of items when generating loot, such as item scrap or command essence.
	 */
	public boolean isSpecialItem() {
		return false;
	}
}
