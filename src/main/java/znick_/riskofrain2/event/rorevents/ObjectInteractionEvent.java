package znick_.riskofrain2.event.rorevents;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
public class ObjectInteractionEvent extends PlayerEvent {

	private final ObjectType type;
	
	public ObjectInteractionEvent(EntityPlayer player, ObjectType type) {
		super(player);
		this.type = type;
	}
	
	public ObjectType getObjectType() {
		return this.type;
	}
	
	public static enum ObjectType {
		SMALL_CHEST,
		LARGE_CHEST,
		GOLD_CHEST,
		LUNAR_POD,
		PRINTER_3D,
		BARREL,
		SHOP_TERMINAL,
		SHRINE_OF_CHANCE,
		SHRINE_OF_THE_MOUNTAIN,
		SHRINE_OF_COMBAT,
		SHRINE_OF_GOLD,
		SHRINE_OF_ORDER,
		NEWT_ALTAR,
		TELEPORTER,
		BLUE_PORTAL,
		GOLD_PORTAL,
		NULL_PORTAL,
		CLEANSING_POOL,
		LUNAR_BUD,
		GREEN_CAULDRON,
		RED_CAULDRON,
		LUNAR_SEER,
		LUNAR_SHOP_REFRESHER,
	}

}
