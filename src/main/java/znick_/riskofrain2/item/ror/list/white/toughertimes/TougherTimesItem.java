package znick_.riskofrain2.item.ror.list.white.toughertimes;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnDeathItem;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class TougherTimesItem extends RiskOfRain2Item implements OnHurtItem, OnDeathItem {

	public TougherTimesItem() {
		super("tougher_times");
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
		return "Chance to block incoming damage.";
	}
	
	@Override
	public boolean isUnlockedByDefault() {
		return false;
	}

	@Override
	public void procOnHurt(LivingHurtEvent event, AbstractEntityData entity, int itemCount) {
		entity.playSound("ror2:tougher_times");
		event.setCanceled(true);
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, AbstractEntityData entity, int itemCount) {
		return Math.random() < 1 - 1 / (1 + 0.15 * itemCount);
	}

	@Override
	public boolean shouldProcOnDeath(LivingDeathEvent event, AbstractEntityData entity, int itemCount) {
		return entity.isPlayer();
	}

	@Override
	public void procOnDeath(LivingDeathEvent event, AbstractEntityData entity, int itemCount) {
		PlayerData player = (PlayerData) entity;
		player.addDeath();
		if (player.getDeathCount() == 5) player.unlock(this);
	}
}
