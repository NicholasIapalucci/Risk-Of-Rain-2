package znick_.riskofrain2.api.ror.ability;

public class AbilityWrapper {

	private int cooldown;
	private int charges;
	private boolean isReady;
	
	public AbilityWrapper(int cooldown, int charges, boolean isReady) {
		this.cooldown = cooldown;
		this.charges = charges;
		this.isReady = isReady;
	}
	
	public AbilityWrapper() {

	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public int getCooldown() {
		return this.cooldown;
	}
	
	public void setCharges(int charges) {
		this.charges = charges;
	}
	
	public int getCharges() {
		return this.charges;
	}
	
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	
	public boolean isReady() {
		return this.isReady;
	}
}
