package znick_.riskofrain2.item.ror.list.white.bustlingfungus;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class BustlingFungusItem extends RiskOfRain2Item implements OnUpdateItem {

	private static final Map<EntityPlayer, Integer> LAST_MOVED_MAP = new HashMap<>();
	
	public BustlingFungusItem() {
		super("bustling_fungus");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.HEALING;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Heal all nearby allies after standing still for 2 seconds.";
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return true;
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		// If the player is moving, note that the last time they moved is now
		if (player.isMoving()) LAST_MOVED_MAP.put(player.getPlayer(), TickHandler.client());
		
		// If it has been more than 2 seconds since they last moved, activate the bustling fungus
		else if (TickHandler.client() - LAST_MOVED_MAP.get(player.getPlayer()) > TickHandler.fromSeconds(2)) {
			
		}
	}

}
