package znick_.riskofrain2.util.achievement;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class RiskOfRain2Achievements {

	private static final Map<RiskOfRain2Item, Achievement> ACHIEVEMENTS = generateAchievements();
	private static final AchievementPage ACHIEVEMENT_PAGE = new AchievementPage("Risk Of Rain 2", ACHIEVEMENTS.values().toArray(new Achievement[0]));
	
	public static Map<RiskOfRain2Item, Achievement> generateAchievements() {
		Map<RiskOfRain2Item, Achievement> achievements = new LinkedHashMap<RiskOfRain2Item, Achievement>();
		for (RiskOfRain2Item item : RiskOfRain2Items.ITEM_SET) {
			if (!item.isUnlockedByDefault()) {
				Achievement achievement = item.getAchievement();
				achievement.initIndependentStat().registerStat();
				achievements.put(item, achievement);
			}
		}
		return achievements;
	}
	
	public static void registerAchievements() {
		AchievementPage.registerAchievementPage(ACHIEVEMENT_PAGE);
	}
	
	public static Achievement fromItem(RiskOfRain2Item item) {
		return ACHIEVEMENTS.get(item);
	}
}
