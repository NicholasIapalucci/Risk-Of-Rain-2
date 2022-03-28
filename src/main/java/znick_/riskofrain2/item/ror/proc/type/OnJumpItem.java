package znick_.riskofrain2.item.ror.proc.type;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;

public interface OnJumpItem {

	public abstract void procOnJump(LivingJumpEvent event, AbstractEntityData player, int itemCount);
	
	public abstract boolean shouldProcOnJump(LivingJumpEvent event, AbstractEntityData player, int itemCount);
}
