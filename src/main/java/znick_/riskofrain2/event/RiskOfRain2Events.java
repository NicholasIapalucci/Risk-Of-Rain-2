package znick_.riskofrain2.event;

import znick_.riskofrain2.api.ror.survivor.SurvivorEventHandler;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.event.handler.GeneralEventHandler;
import znick_.riskofrain2.event.handler.KeyBindListener;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.ror.proc.ItemProccer;

public class RiskOfRain2Events {
	
	public static final EventHandler SURVIVOR_EVENT_HANDLER = new SurvivorEventHandler();
	public static final EventHandler ITEM_PROCCER = new ItemProccer();
	public static final EventHandler TICK_HANDLER = new TickHandler();
	public static final EventHandler GENERAL_EVENT_HANDLER = new GeneralEventHandler();
	public static final EventHandler KEYBIND_LISTENER = new KeyBindListener();
	
	public static void registerEvents() {}
}
