package znick_.riskofrain2.item.ror.list.equipment.elite.ifritsdistinction;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.entity.elite.EliteType;
import znick_.riskofrain2.item.ror.list.equipment.elite.EliteEquipmentItem;
import znick_.riskofrain2.item.ror.proc.type.OnHitItem;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;

public class IfritsDistinctionItem extends EliteEquipmentItem implements OnUpdateItem, OnHitItem {

	public IfritsDistinctionItem() {
		super("ifrits_distinction", EliteType.BLAZING);
	}

	@Override
	public String getDescription() {
		return "Become an aspect of fire.";
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount) {
		return true;
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, AbstractEntityData entity, int itemCount) {
		entity.addBuff(new IfritsDistinctionBuff());
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount) {
		return true;
	}
	
	@Override
	public void procOnHit(LivingAttackEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount) {
		enemy.setFire(2);
	}
}
