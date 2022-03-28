package znick_.riskofrain2.item.ror.proc.type;

import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;

public interface OnKeyPressItem {
	public abstract void procOnKeyPress(KeyInputEvent event, AbstractEntityData player, int itemCount);
	public abstract boolean shouldProcOnKeypress(KeyInputEvent event, AbstractEntityData player, int itemCount);
}
