package znick_.riskofrain2.block.itemgen;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.list.ScrapItem;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public interface ItemGenerator {
	
	public abstract RiskOfRain2Item generateItem(EntityPlayer player);
	
	/**
	 * Generates a random item with the given rarity.
	 * 
	 * @param rarity The rarity of the item to generate
	 * @return The item generated
	 */
	public default RiskOfRain2Item generateItem(ItemRarity rarity, EntityPlayer player) {
		// Create an array of all items of the given rarity
		RiskOfRain2Item[] items = PlayerData.get(player).getUnlockedItems(rarity);
		// Remove item scrap
		items = Arrays.stream(items).filter(item -> !(item instanceof ScrapItem)).toArray(RiskOfRain2Item[]::new);
		// Return a random item from the array
		return items[new Random().nextInt(items.length)];
	}
}
