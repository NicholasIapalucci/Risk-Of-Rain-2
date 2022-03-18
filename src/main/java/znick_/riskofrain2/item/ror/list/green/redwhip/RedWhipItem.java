package znick_.riskofrain2.item.ror.list.green.redwhip;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import scala.actors.threadpool.Arrays;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHitItem;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class RedWhipItem extends RiskOfRain2Item implements OnHitItem, OnUpdateItem {

	public RedWhipItem() {
		super("red_whip");
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
		return "Move fast out of combat.";
	}
	
	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount) {
		return true;
	}
	
	@Override
	public void procOnHit(LivingAttackEvent event, AbstractEntityData entity, EntityLivingBase enemy, int itemCount) {
		entity.addBuff(new RedWhipCooldownBuff(itemCount));
		entity.removeBuff(RedWhipBuff.class);
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount) {
		return !player.hasBuff(RedWhipCooldownBuff.class);
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount) {
		player.addBuff(new RedWhipBuff(itemCount));
	}

}
