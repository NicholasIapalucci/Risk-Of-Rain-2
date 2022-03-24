package znick_.riskofrain2.item.ror.list.green;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class OldWarStealthkit extends RiskOfRain2Item implements OnUpdateItem {
	
	public OldWarStealthkit() {
		super("old_war_stealthkit");
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, EntityData player, int itemCount) {
		player.getEntity().addPotionEffect(new PotionEffect(Potion.invisibility.id, 100, 0));
		player.addToStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, 0.4);
		//TODO: Cooldown buff
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, EntityData player, int itemCount) {
		// Return false if on cooldown
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
