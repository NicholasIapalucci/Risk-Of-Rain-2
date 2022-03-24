package znick_.riskofrain2.item.ror.list.white.delicatewatch;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.consume.ConsumableItem;
import znick_.riskofrain2.item.ror.dlc.DLC;
import znick_.riskofrain2.item.ror.proc.type.OnHitItem;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class DelicateWatchItem extends RiskOfRain2Item implements ConsumableItem, OnHurtItem, OnHitItem {

	public DelicateWatchItem() {
		super("delicate_watch");
	}

	@Override
	public DLC getDLC() {
		return DLC.SURVIVORS_OF_THE_VOID;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.DAMAGE;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Deal bonus damage. Breaks at low health.";
	}
	
	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, EntityData player, int itemCount) {
		return true;
	}
	
	@Override
	public void procOnHurt(LivingHurtEvent event, EntityData player, int itemCount) {
		if (player.getHealth() < player.getMaxHealth() * 0.25) this.consume(player);
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, EntityData player, EntityLivingBase enemy, int itemCount) {
		return true;
	}
	
	@Override
	public void procOnHit(LivingAttackEvent event, EntityData player, EntityLivingBase enemy, int itemCount) {
		player.addToStat(EntityStat.DAMAGE_MULTIPLIER, 0.2);
	}

	@Override
	public void consume(EntityData player) {
		player.replaceAllItems(this, this.getBrokenItem());
	}

	@Override
	public RiskOfRain2Item getBrokenItem() {
		return RiskOfRain2Items.BROKEN_DELICATE_WATCH;
	}

}
