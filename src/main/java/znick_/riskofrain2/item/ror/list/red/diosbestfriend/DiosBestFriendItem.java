package znick_.riskofrain2.item.ror.list.red.diosbestfriend;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.consume.ConsumableItem;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class DiosBestFriendItem extends RiskOfRain2Item implements OnHurtItem, ConsumableItem {

	private static final Map<EntityPlayer, Integer> IMMUNE_PLAYERS = new HashMap<>();
	
	public DiosBestFriendItem() {
		super("dios_best_friend");
	}

	@Override
	public void procOnHurt(LivingHurtEvent event, EntityData player, int itemCount) {
		event.setCanceled(true);
		player.addBuff(new DiosBestFriendBuff(itemCount));
		this.consume(player);
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, EntityData player, int itemCount) {
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

	@Override
	public void consume(EntityData player) {
		player.replaceItem(this, this.getBrokenItem());
	}

	@Override
	public RiskOfRain2Item getBrokenItem() {
		return RiskOfRain2Items.DIOS_BEST_FRIEND_CONSUMED;
	}

}
