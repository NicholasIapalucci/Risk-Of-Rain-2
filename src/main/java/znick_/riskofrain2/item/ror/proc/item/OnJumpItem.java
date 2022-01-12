package znick_.riskofrain2.item.ror.proc.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import znick_.riskofrain2.api.mc.PlayerData;

public interface OnJumpItem {

	public abstract void procOnJump(LivingJumpEvent event, PlayerData player, int itemCount);
	
	public abstract boolean shouldProcOnJump(LivingJumpEvent event, PlayerData player, int itemCount);
}
