package znick_.riskofrain2.item.ror.proc.type;

import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import znick_.riskofrain2.api.mc.data.EntityData;

public interface OnKeyPressItem {
	public abstract void procOnKeyPress(KeyInputEvent event, EntityData player, int itemCount);
	public abstract boolean shouldProcOnKeypress(KeyInputEvent event, EntityData player, int itemCount);
}
