package znick_.riskofrain2.item.armor;

public enum ArmorType {
	
	HELMET(0, "Helmet"),
	CHESTPLATE(1, "Chestplate"),
	LEGGINGS(2, "Leggings"),
	BOOTS(3, "Boots");
	
	private final int id;
	private final String name;
	
	private ArmorType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public final int getID() {
		return this.id;
	}
	
	public final String getName() {
		return this.name;
	}
	
	public final ArmorType getArmorTypeFromID(int id) {
		if (id < 0 || id > 3) throw new IllegalArgumentException("ArmorType IDs range from 0-3 double inclusive.");
		for (ArmorType t : ArmorType.values()) {
			if (t.id == id) return t;
		}
		throw new UnsupportedOperationException("There is no ArmorType with the ID " + id);
	}
}
