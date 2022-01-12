package znick_.riskofrain2.util.misc.customs;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.achievement.AchievementItem;

public class RiskOfRain2Achievements {

	public static AchievementPage achievementPageRiskOfRain2; //TODO: Add achievements
	public static final Map<RiskOfRain2Item, Achievement> achievementMap = new LinkedHashMap<RiskOfRain2Item, Achievement>();
	public static final Set<Achievement> achievements = new LinkedHashSet<Achievement>();

	public static void registerAchievements() throws Exception {
		int i = 0;
		int j = 0;
		for (RiskOfRain2Item item : RiskOfRain2Items.LOCKED_ITEMS.keySet()) {
			String name = item.getUnlocalizedName().substring(5);
			Achievement achievement = new Achievement("achievement." + name, name, i, j, new ItemStack(RiskOfRain2Items.LOCKED_ITEMS.get(item)), (Achievement) null);
			achievementMap.put(item, achievement);
			achievements.add(achievement);
			achievement.initIndependentStat().registerStat();
			i++;
			if (i == 13) {
				i = 0;
				j++;
			}
		}
		
		achievementPageRiskOfRain2 = new AchievementPage("Risk Of Rain 2", achievements.toArray(new Achievement[0]));
		AchievementPage.registerAchievementPage(achievementPageRiskOfRain2);
	}
}
