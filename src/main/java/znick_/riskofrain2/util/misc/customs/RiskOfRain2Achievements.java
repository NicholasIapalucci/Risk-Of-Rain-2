package znick_.riskofrain2.util.misc.customs;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.RiskOfRain2Items;

public class RiskOfRain2Achievements {

	public static final Set<Achievement> ACHIEVEMENTS = generateAchievements();
	public static final AchievementPage ACHIEVEMENT_PAGE = new AchievementPage("Risk Of Rain 2", ACHIEVEMENTS.toArray(new Achievement[0]));

	private static Set<Achievement> generateAchievements() {
		Set<Achievement> achievements = new LinkedHashSet<Achievement>();
		int i = 0;
		int j = 0;
		for (RiskOfRain2Item item : RiskOfRain2Items.ITEM_SET) {
			String name = item.getUnlocalizedName().substring(5);
			Achievement achievement = new Achievement("achievement." + name, name, i, j, new ItemStack(item), (Achievement) null);
			achievements.add(achievement);
			achievement.initIndependentStat().registerStat();
			i++;
			if (i == 13) {
				i = 0;
				j++;
			}
		}
		
		return achievements;
	}
	
	public static void registerAchievements() {
		AchievementPage.registerAchievementPage(ACHIEVEMENT_PAGE);
	}
}
