package znick_.riskofrain2.api.ror.items.proc.type;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;

public interface OnJumpItem {

	public abstract void procOnJump(LivingJumpEvent event, PlayerData player, int itemCount);
	
	public abstract boolean shouldProcOnJump(LivingJumpEvent event, PlayerData player, int itemCount);
}
