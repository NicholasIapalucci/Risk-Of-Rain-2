package znick_.riskofrain2.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;

public class RandomGenerator {
	
	private static final Set<RiskOfRain2Item> WHITE_ITEMS = new HashSet<>();
	private static final Set<RiskOfRain2Item> GREEN_ITEMS = new HashSet<>();
	private static final Set<RiskOfRain2Item> RED_ITEMS = new HashSet<>();
	
	private static final Random RANDOM = new Random();
	
	public static void registerItem(RiskOfRain2Item item) {
		switch(item.getRarity()) {
		case WHITE: 
			WHITE_ITEMS.add(item); 
			return;
		case GREEN: 
			GREEN_ITEMS.add(item); 
			return;
		case RED: 
			RED_ITEMS.add(item); 
			return;
		default:
			return;
		}
	}
	
	public static RiskOfRain2Item generateSmallChestItem() {
		double rand = Math.random();
		if (rand < 0.099) return generateRedItem();
		if (rand < 0.198) return generateGreenItem();
		return generateWhiteItem();
	}
	
	public static RiskOfRain2Item generateBigChestItem() {
		return Math.random() < 0.2 ? generateRedItem() : generateGreenItem();
	}
	
	public static RiskOfRain2Item generateWhiteItem() {
		return WHITE_ITEMS.toArray(new RiskOfRain2Item[0])[RANDOM.nextInt(WHITE_ITEMS.size())];
	}
	
	public static RiskOfRain2Item generateGreenItem() {
		return GREEN_ITEMS.toArray(new RiskOfRain2Item[0])[RANDOM.nextInt(GREEN_ITEMS.size())];
	}
	
	public static RiskOfRain2Item generateRedItem() {
		return RED_ITEMS.toArray(new RiskOfRain2Item[0])[RANDOM.nextInt(RED_ITEMS.size())];
	}
}
