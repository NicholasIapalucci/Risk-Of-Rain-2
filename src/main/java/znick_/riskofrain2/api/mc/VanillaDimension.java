package znick_.riskofrain2.api.mc;

public enum VanillaDimension {

	NETHER(-1),
	OVERWORLD(0),
	END(1);
	
	private final int id;
	
	private VanillaDimension(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
}
