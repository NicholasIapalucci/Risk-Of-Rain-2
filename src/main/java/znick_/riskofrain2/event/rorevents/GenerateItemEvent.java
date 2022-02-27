package znick_.riskofrain2.event.rorevents;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;

/**
 * The {@code GenerateItemEvent} is fired when a Risk Of Rain 2 item is generated into the world. The event
 * is cancelable, and if it is canceled, the item will not generate. However, certain consequences of the
 * item being generated will still happen. For example, if a small chest is opened and the event to generate
 * the item is canceled, the small chest will still be opened and unopenable in the future. Each generation
 * source has their own events to affect that behavior.
 * 
 * @author zNick_
 */
@Cancelable
public class GenerateItemEvent extends Event {

	private RiskOfRain2Item item;
	private final GenerationSource source;
	
	/**
	 * Creates a new {@code GenerateItemEvent}.
	 * 
	 * @param source The source that generated the item
	 * @param item The item that was generated
	 */
	public GenerateItemEvent(GenerationSource source, RiskOfRain2Item item) {
		this.item = item;
		this.source = source;
	}
	
	public void setItem(RiskOfRain2Item item) {
		this.item = item;
	}
	
	public RiskOfRain2Item getItem() {
		return this.item;
	}
	
	private GenerationSource getSource() {
		return this.source;
	}

	public static enum GenerationSource {
		SMALL_CHEST,
		LARGE_CHEST,
		RUSTY_LOCKBOX,
		LUNAR_POD,
		EQUIPMENT_BARREL,
		GOLD_CHEST,
		PRINTER_3D,
		SHOP_TERMINAL;
	}
	
}
