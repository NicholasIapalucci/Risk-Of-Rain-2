package znick_.riskofrain2.api.ror.buff;

import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.event.TickHandler;

/**
 * Represents a temporary {@code Buff} that wears off after a certain amount of time.
 * 
 * @author zNick_
 */
public abstract class DurationBuff extends Buff {
	
	private int startTick;
	private int duration;
	
	public DurationBuff(RiskOfRain2Item item, int itemCount, int duration) {
		super(item, itemCount);
		this.startTick = TickHandler.server();
		this.duration = duration;
	}
	
	public  int getStartTick() {
		return this.startTick;
	}
	
	public void setStartTick(int newTick) {
		this.startTick = newTick;
	}
	
	public void resetStartTick() {
		this.startTick = TickHandler.server();
	}
	
	public int getDuration() {
		return this.duration;
	}
}
