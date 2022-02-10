package znick_.riskofrain2.api.ror.items.list.red.diosbestfriend;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.proc.type.OnHurtItem;
import znick_.riskofrain2.api.ror.items.proc.type.OnUpdateItem;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.helper.MinecraftHelper;

public class DiosBestFriendItem extends RiskOfRain2Item implements OnHurtItem {

	private static final Map<EntityPlayer, Integer> IMMUNE_PLAYERS = new HashMap<>();
	
	public DiosBestFriendItem() {
		super("dios_best_friend");
	}

	@Override
	public void procOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		event.setCanceled(true);
		player.addBuff(new DiosBestFriendBuff(itemCount));
		MinecraftHelper.removeAmount(player.getPlayer(), RiskOfRain2Items.DIOS_BEST_FRIEND, 1);
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		return event.ammount >= player.getHealth();
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
