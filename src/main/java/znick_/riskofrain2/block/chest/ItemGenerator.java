package znick_.riskofrain2.block.chest;

import java.util.Map;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.util.helper.MapHelper;

public interface ItemGenerator {
	
	public static final Random RANDOM = new Random();
	
	public abstract Map<ItemRarity, Double> getRarityWeights();
	
	public default RiskOfRain2Item generateItem(EntityPlayer playerEntity) {
		PlayerData player = PlayerData.get(playerEntity);
		Map<ItemRarity, Double> rarityWeights = MapHelper.sortByValue(this.getRarityWeights());
		double rand = Math.random();
		
		ItemRarity mostCommon = null;
		for (Map.Entry<ItemRarity, Double> entry : rarityWeights.entrySet()) {
			if (rand < entry.getValue()) return this.generateItem(entry.getKey(), player);
			mostCommon = entry.getKey();
		}
		
		return this.generateItem(mostCommon, player);
	}
	
	public default RiskOfRain2Item generateItem(ItemRarity rarity, PlayerData player) {
		RiskOfRain2Item[] possibleItems = player.getUnlockedItems(rarity);
		return possibleItems[RANDOM.nextInt(possibleItems.length)];
	}
}
