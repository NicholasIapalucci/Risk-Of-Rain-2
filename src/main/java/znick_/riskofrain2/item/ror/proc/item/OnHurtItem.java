package znick_.riskofrain2.item.ror.proc.item;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.PlayerData;

public interface OnHurtItem {
	public abstract void procOnHurt(LivingHurtEvent event, PlayerData player, int itemCount);
	public abstract boolean shouldProcOnHurt(LivingHurtEvent event, PlayerData player, int itemCount);
}
