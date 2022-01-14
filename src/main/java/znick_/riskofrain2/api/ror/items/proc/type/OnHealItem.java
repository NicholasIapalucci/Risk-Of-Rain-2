package znick_.riskofrain2.api.ror.items.proc.type;

import net.minecraftforge.event.entity.living.LivingHealEvent;
import znick_.riskofrain2.api.mc.PlayerData;

public interface OnHealItem {

	public abstract void procOnHeal(LivingHealEvent event, PlayerData player, int itemCount);
}
