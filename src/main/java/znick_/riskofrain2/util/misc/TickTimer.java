package znick_.riskofrain2.util.misc;

public class TickTimer {

	private int startTick;
	private int endTick;
	private int duration;
	
	public TickTimer() {
		
	}
	
	public TickTimer setStartTick(int startTick) {
		this.startTick = startTick;
		this.endTick = this.startTick + this.duration;
		return this;
	}
	
	public int getStartTick() {
		return this.startTick;
	}
	
	public TickTimer setEndTick(int endTick) {
		this.endTick = endTick;
		this.duration = this.endTick - this.startTick;
		return this;
	}
	
	public int getEndTick() {
		return this.endTick;
	}
	
	public boolean isFinished(int currentTick) {
		return currentTick > this.endTick;
	}
	
	public TickTimer setDuration(int duration) {
		this.setEndTick(duration);
		return this;
	}
	
	public int getDuration() {
		return this.duration;
	}
}
