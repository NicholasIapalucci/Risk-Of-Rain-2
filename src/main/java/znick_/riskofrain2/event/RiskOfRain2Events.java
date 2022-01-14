package znick_.riskofrain2.event;

import znick_.riskofrain2.api.ror.items.proc.ItemProccer;
import znick_.riskofrain2.event.handler.CombatHandler;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.event.handler.GeneralEventHandler;
import znick_.riskofrain2.event.handler.character.HuntressEventHandler;
import znick_.riskofrain2.event.handler.item.EquipmentEventHandler;

public class RiskOfRain2Events {
	
	public static final EventHandler HUNTRESS_EVENT_HANDLER = new HuntressEventHandler();
	public static final EventHandler ITEM_PROCCER = new ItemProccer();
	public static final EventHandler TICK_HANDLER = new Tick();
	public static final EventHandler COMBAT_HANDLER = new CombatHandler();
	public static final EventHandler EQUIPMENT_HANDLER = new EquipmentEventHandler();
	public static final EventHandler GENERAL_EVENT_HANDLER = new GeneralEventHandler();
	
	public static void registerEvents() {}
}
