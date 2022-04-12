package znick_.riskofrain2.item.ror.list;

import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

/**
 * Class for creating "Item Scrap" of various rarities. This is the only class that creates multiple different
 * items in the game. All rarities have an "item scrap" item associated with it, aside from lunar items and
 * equipment. Scrap does nothing, but it is prioritized when using 3D printers. 
 * 
 * @author zNick_
 */
public class ScrapItem extends RiskOfRain2Item {

	/**The rarity of the scrap*/
	private final ItemRarity rarity;
	
	/**
	 * Creates a new item scrap. Each rarity gets one instance of the {@code ScrapItem} class.
	 * 
	 * @param rarity The rarity of the item scrap.
	 */
	public ScrapItem(ItemRarity rarity) {
		super(rarity.toString().toLowerCase() + "_scrap");
		this.rarity = rarity;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UNKNOWN;
	}

	@Override
	public ItemRarity getRarity() {
		return this.rarity;
	}

	@Override
	public String getDescription() {
		return "Does nothing. Prioritized when used with 3D Printers.";
	}
	
	@Override
	public boolean isExcludedFromChests() {
		return true;
	}
}
