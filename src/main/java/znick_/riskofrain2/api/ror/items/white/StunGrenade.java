package znick_.riskofrain2.api.ror.items.white;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.event.Tick;
import znick_.riskofrain2.item.ror.proc.item.OnHitItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class StunGrenade extends RiskOfRain2Item implements OnHitItem {
	
	public StunGrenade() {
		super("stun_grenade");
	}

	@Override
	public void procOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		return player.rollStat(1 - 1 / (1 + 0.05f * itemCount));
	}
	
	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Chance to stun on hit.";
	}
}
