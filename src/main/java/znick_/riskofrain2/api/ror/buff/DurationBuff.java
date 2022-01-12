package znick_.riskofrain2.api.ror.buff;

import znick_.riskofrain2.event.Tick;

public abstract class DurationBuff extends Buff {
	
	private int startTick;
	private int duration;
	
	public DurationBuff(int itemCount, int duration) {
		super(itemCount);
		this.startTick = Tick.server();
		this.duration = duration;
	}
	
	public  int getStartTick() {
		return this.startTick;
	}
	
	public void setStartTick(int newTick) {
		this.startTick = newTick;
	}
	
	public void resetStartTick() {
		this.startTick = Tick.server();
	}
	
	public int getDuration() {
		return this.duration;
	}
}
