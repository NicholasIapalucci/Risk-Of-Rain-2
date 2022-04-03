package znick_.riskofrain2.block.itemgen;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.list.ScrapItem;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public interface ItemGenerator {
	
	public abstract RiskOfRain2Item generateItem(EntityPlayer player);
	
	/**
	 * Generates a random item with the given rarity. Does not include "special" items such as consumed
	 * items or item scrap.
	 * 
	 * @param rarity The rarity of the item to generate
	 * 
	 * @return The item generated
	 */
	public default RiskOfRain2Item generateItem(ItemRarity rarity, EntityPlayer player) {
		// Create an array of all items of the given rarity
		RiskOfRain2Item[] items = AbstractEntityData.get(player).getUnlockedItems(rarity);
		// Remove item scrap
		items = Arrays.stream(items).filter(item -> !(item instanceof ScrapItem)).toArray(RiskOfRain2Item[]::new);
		// Return a random item from the array
		return items[new Random().nextInt(items.length)];
	}
	
	public static RiskOfRain2Item generateItem(ItemRarity rarity) {
		// Create an array of all items of the given rarity
		RiskOfRain2Item[] items = RiskOfRain2Items.ITEM_SET.toArray(new RiskOfRain2Item[0]);
		// Remove item scrap
		items = Arrays.stream(items).filter(item -> !(item instanceof ScrapItem)).toArray(RiskOfRain2Item[]::new);
		// Return a random item from the array
		return items[new Random().nextInt(items.length)];
	}
}
