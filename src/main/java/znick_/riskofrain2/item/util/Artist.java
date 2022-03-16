package znick_.riskofrain2.item.util;

public enum Artist {
	
	NICK("zNick_"),
	AIDAN("PixelNebula303");
	
	private final String name;
	
	private Artist(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
