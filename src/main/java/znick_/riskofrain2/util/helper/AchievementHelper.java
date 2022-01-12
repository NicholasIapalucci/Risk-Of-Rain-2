package znick_.riskofrain2.util.helper;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.misc.customs.RiskOfRain2Achievements;

public class AchievementHelper {

	public static Achievement getAchievement(RiskOfRain2Item item) {
		return RiskOfRain2Achievements.achievementMap.get(item);
	}
	
	public static void unlockAchievement(EntityPlayer player, RiskOfRain2Item item) {
		try {
			Achievement a = getAchievement(item);
			player.addStat(a, 1);
			Field field = Achievement.class.getDeclaredField("theItemStack");
			field.setAccessible(true);
			FieldUtils.removeFinalModifier(field);
			field.set(a, new ItemStack(RiskOfRain2Items.UNLOCKED_ITEMS.get(item)));
		} 
		catch(Exception e) {throw new RuntimeException(e);}
	}
}
