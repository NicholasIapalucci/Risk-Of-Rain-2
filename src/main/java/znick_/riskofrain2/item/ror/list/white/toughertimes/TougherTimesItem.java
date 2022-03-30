package znick_.riskofrain2.item.ror.list.white.toughertimes;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.onDeathListener;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.util.achievement.RiskOfRain2Achievement;

public class TougherTimesItem extends RiskOfRain2Item implements OnHurtItem {

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
	public RiskOfRain2Achievement getAchievement() {
		return TougherTimesAchievement.INSTANCE;
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
}
