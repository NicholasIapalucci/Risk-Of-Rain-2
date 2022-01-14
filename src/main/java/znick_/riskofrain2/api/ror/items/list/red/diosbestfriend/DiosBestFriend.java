package znick_.riskofrain2.api.ror.items.list.red.diosbestfriend;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.proc.type.OnHurtItem;
import znick_.riskofrain2.api.ror.items.proc.type.OnUpdateItem;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;
import znick_.riskofrain2.event.Tick;

public class DiosBestFriend extends RiskOfRain2Item implements OnHurtItem {

	private static final Map<EntityPlayer, Integer> IMMUNE_PLAYERS = new HashMap<>();
	
	public DiosBestFriend() {
		super("dios_best_friend");
	}

	@Override
	public void procOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		event.setCanceled(true);
		player.addBuff(new DiosBestFriendBuff(itemCount)); //TODO: Add Dio Consumption
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		return event.ammount >= player.getHealth();
	}

	@Override
	public String getProperName() {
		return "Dio's Best Friend";
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.RED;
	}

	@Override
	public String getDescription() {
		return "Cheat death. Consumed on use.";
	}

}
