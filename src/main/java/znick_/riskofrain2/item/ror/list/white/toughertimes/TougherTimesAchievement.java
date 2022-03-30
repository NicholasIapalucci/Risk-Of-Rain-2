package znick_.riskofrain2.item.ror.list.white.toughertimes;

import java.util.UUID;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.achievement.RiskOfRain2Achievement;

public class TougherTimesAchievement extends RiskOfRain2Achievement {
	
	private static final UUID DEATHS_ID = UUID.randomUUID();

	public static final TougherTimesAchievement INSTANCE = new TougherTimesAchievement();
	
	private TougherTimesAchievement() {
		super(RiskOfRain2Items.TOUGHER_TIMES);
	}
	
	@SubscribeEvent
	public void onDeath(PlayerEvent.Clone event) {
		if (event.entity instanceof EntityPlayer && event.wasDeath) {
			PlayerData player = PlayerData.get((EntityPlayer) event.entity);
			if (player.getSavedObject(DEATHS_ID, Integer.class) == null) player.saveObject(DEATHS_ID, 0);
			player.saveObject(DEATHS_ID, player.getSavedObject(DEATHS_ID, int.class) + 1);
			
			if (player.getSavedObject(DEATHS_ID, int.class) == 5) player.unlock(RiskOfRain2Items.TOUGHER_TIMES);
		}
	}
}
