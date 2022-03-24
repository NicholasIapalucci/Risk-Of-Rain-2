package znick_.riskofrain2.item.ror.proc.type;

import net.minecraftforge.event.entity.living.LivingHealEvent;
import znick_.riskofrain2.api.mc.data.EntityData;

public interface OnHealItem {

	public abstract void procOnHeal(LivingHealEvent event, EntityData player, int itemCount);
}
