package znick_.riskofrain2.item.ror.list.equipment.elite;

import java.util.HashMap;
import java.util.Map;

import znick_.riskofrain2.entity.elite.EliteType;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public abstract class EliteEquipmentItem extends RiskOfRain2Item {

	private static final Map<EliteType, EliteEquipmentItem> ELITE_ITEMS = new HashMap<>();
	
	private final EliteType eliteType;
	
	protected EliteEquipmentItem(String name, EliteType type) {
		super(name);
		this.eliteType = type;
		type.setItem(this);
		ELITE_ITEMS.put(type, this);
	}
	
	public static EliteEquipmentItem getItemFromEliteType(EliteType type) {
		return ELITE_ITEMS.get(type);
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UNKNOWN;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.EQUIPMENT;
	}
	
	@Override
	public boolean isSpecial() {
		return true;
	}

}
