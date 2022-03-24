package znick_.riskofrain2.item.ror.proc.type;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import znick_.riskofrain2.api.mc.data.EntityData;

public interface OnJumpItem {

	public abstract void procOnJump(LivingJumpEvent event, EntityData player, int itemCount);
	
	public abstract boolean shouldProcOnJump(LivingJumpEvent event, EntityData player, int itemCount);
}
