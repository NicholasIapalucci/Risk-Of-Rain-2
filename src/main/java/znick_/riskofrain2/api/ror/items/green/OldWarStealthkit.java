package znick_.riskofrain2.api.ror.items.green;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.event.Tick;
import znick_.riskofrain2.item.ror.proc.item.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class OldWarStealthkit extends RiskOfRain2Item implements OnUpdateItem {

	private static final Map<EntityPlayer, Integer> PLAYERS_ON_COOLDOWN = new HashMap<>();
	
	public OldWarStealthkit() {
		super("old_war_stealthkit");
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		player.getPlayer().addPotionEffect(new PotionEffect(Potion.invisibility.id, 100, 0));
		player.addToStat(PlayerStat.MOVEMENT_SPEED_MULTIPLIER, 0.4);
		PLAYERS_ON_COOLDOWN.put(player.getPlayer(), Tick.server());
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		if (PLAYERS_ON_COOLDOWN.containsKey(player.getPlayer())) {
			if (Tick.server() > PLAYERS_ON_COOLDOWN.get(player.getPlayer()) / (Math.pow(2, itemCount - 1))) {
				PLAYERS_ON_COOLDOWN.remove(player.getPlayer());
			}
			return false;
		}
		return player.getHealth() < player.getMaxHealth() * 0.25;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.GREEN;
	}

	@Override
	public String getDescription() {
		return "Turn invisible at low health";
	}
}
