package znick_.riskofrain2.item.ror.proc.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import znick_.riskofrain2.api.mc.PlayerData;

public interface OnKillItem {

	public abstract void procOnKill(LivingDeathEvent event, PlayerData player, EntityLivingBase enemy, int itemCount);
}
