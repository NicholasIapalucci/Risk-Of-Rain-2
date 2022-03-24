package znick_.riskofrain2.item.ror.proc.type;

import net.minecraft.entity.EntityLivingBase;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.event.rorevents.ObjectInteractionEvent;

public interface OnObjectInteractionItem {

	public abstract void procOnInteraction(ObjectInteractionEvent event, EntityData player, int itemCount);
}
