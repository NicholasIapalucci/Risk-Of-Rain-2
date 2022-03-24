package znick_.riskofrain2.item.ror.proc.type;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import znick_.riskofrain2.api.mc.data.EntityData;

public interface OnKillItem {

	public abstract void procOnKill(LivingDeathEvent event, EntityData player, EntityLivingBase enemy, int itemCount);
}
