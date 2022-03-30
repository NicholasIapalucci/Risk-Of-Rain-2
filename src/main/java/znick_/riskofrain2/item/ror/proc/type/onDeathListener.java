package znick_.riskofrain2.item.ror.proc.type;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;

public interface onDeathListener {
	
	public abstract boolean shouldProcOnDeath(LivingDeathEvent event, AbstractEntityData entity, int itemCount);
	public abstract void procOnDeath(LivingDeathEvent event, AbstractEntityData entity, int itemCount);
}
