package znick_.riskofrain2.entity.elite;

import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.list.equipment.elite.EliteEquipmentItem;

/**
 * Enum of various types of elite entities, such as {@link #BLAZING} and {@link #GLACIAL}.
 * 
 * @author zNick_
 */
public enum EliteType {
	
	BLAZING,
	CELESTINE,
	GLACIAL,
	MALACHITE,
	MENDING,
	OVERLOADING,
	PERFECTED,
	VOIDTOUCHED;
	
	/**
	 * The {@link znick_.riskofrain2.item.ror.list.equipment.elite.EliteEquipmentItem EliteEquipmenItem}
	 * associated with this {@code EliteType}. Given to mobs to give them the effect of the elite. Also
	 * can be dropped with a 1/4000 chance to be obtainable for the player. Set in the item constructor.
	 */
	private EliteEquipmentItem item;
	
	/**
	 * Sets the {@link #item} for this elite type.
	 * 
	 * @param item The {@link EliteEquipmentItem} to set.
	 */
	public void setItem(EliteEquipmentItem item) {
		this.item = item;
	}
	
	/**
	 * Returns the {@link #item EliteEquipmentItem} associated with this {@link EliteType}
	 * 
	 * @return The {@code EliteEquipmentItem}
	 */
	public RiskOfRain2Item getItem() {
		return this.item;
	}
}
