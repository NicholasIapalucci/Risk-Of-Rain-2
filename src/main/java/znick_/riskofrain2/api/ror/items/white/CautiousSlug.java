package znick_.riskofrain2.api.ror.items.white;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.event.Tick;
import znick_.riskofrain2.event.handler.CombatHandler;
import znick_.riskofrain2.item.ror.proc.item.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class CautiousSlug extends RiskOfRain2Item implements OnUpdateItem {

	private static final Set<EntityPlayer> SLUGGING_PLAYERS = new HashSet<>();
	
	public CautiousSlug() {
		super("cautious_slug");
	}

	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		if (!player.getPlayer().worldObj.isRemote) player.getPlayer().heal(1); //TODO: Update cautious slug w refactoring
		else if (!SLUGGING_PLAYERS.contains(player.getPlayer())) {
			player.getPlayer().playSound("ror2:cautious_slug_start", 1, 1);
			SLUGGING_PLAYERS.add(player.getPlayer());
		}
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return CombatHandler.ticksSinceLastHurt(player.getPlayer()) > Tick.fromSeconds(7) && (Tick.server() + 1) % (20 / itemCount) == 0;
	}
	
	public static boolean isActiveOnPlayer(EntityPlayer player) {
		return SLUGGING_PLAYERS.contains(player);
	}
	
	public static void disable(EntityPlayer player) {
		if (player.worldObj.isRemote) {
			SLUGGING_PLAYERS.remove(player);
			player.playSound("ror2:cautious_slug_stop", 1, 1);
		}
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
		return "Rapidly heal outside of danger.";
	}
	
}
