package znick_.riskofrain2.item.ror.list.equipment.elite.herbitingembrace;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.entity.elite.EliteType;
import znick_.riskofrain2.item.ror.list.equipment.elite.EliteEquipmentItem;
import znick_.riskofrain2.item.ror.proc.type.OnHitItem;

public class HerBitingEmbraceItem extends EliteEquipmentItem implements OnHitItem {

	public HerBitingEmbraceItem() {
		super("her_biting_embrace", EliteType.GLACIAL);
	}

	@Override
	public String getDescription() {
		return "Become an aspect of ice.";
	}

	@Override
	public void procOnHit(LivingAttackEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount) {
		AbstractEntityData.get(enemy).addBuff(new Slow80Buff(itemCount));
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount) {
		return true;
	}

}
