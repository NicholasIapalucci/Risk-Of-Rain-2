package znick_.riskofrain2.api.mc.data;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class EntityData extends AbstractEntityData<EntityLivingBase> {

	public static final String PROPERTY_ID = "entity_data";
	
	private final Map<RiskOfRain2Item, Integer> items = new LinkedHashMap<>();
	
	protected EntityData(EntityLivingBase entity) {
		super(entity);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		int i = 0;
		for (Map.Entry<RiskOfRain2Item, Integer> itemEntry : this.items.entrySet()) {
			properties.setInteger("item_" + i, Item.getIdFromItem(itemEntry.getKey()));
			properties.setInteger("item_" + i + "_count", itemEntry.getValue());
			i++;
		}
		compound.setTag(PROPERTY_ID, properties);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(PROPERTY_ID);
		int i = 0;
		while(true) {
			int id = properties.getInteger("item_" + i);
			if (id == 0) break;
			int amount = properties.getInteger("item_" + id + "_count");
			this.items.put((RiskOfRain2Item) Item.getItemById(id), amount);
			i++;
		}
	}

	@Override
	public int itemCount(RiskOfRain2Item item) {
		return this.items.get(item);
	}

	@Override
	public void replaceItem(RiskOfRain2Item toReplace, RiskOfRain2Item replaceWith) {
		if (this.hasItem(toReplace)) return;
		this.removeItem(toReplace);
		this.addItem(replaceWith);
	}
	
	public void addItem(RiskOfRain2Item item) {
		this.addItem(item, 1);
	}
	
	public void addItem(RiskOfRain2Item item, int count) {
		if (!this.hasItem(item)) this.items.put(item, 0);
		this.items.put(item, this.items.get(item) + count);
	}

	@Override
	public void removeItem(RiskOfRain2Item toRemove) {
		if (!this.hasItem(toRemove)) return;
		this.items.put(toRemove, this.items.get(toRemove) - 1);
		if (this.items.get(toRemove) < 1) this.items.remove(toRemove);
	}

	@Override
	public boolean hasItem(RiskOfRain2Item item) {
		return this.items.containsKey(item);
	}

	@Override
	public Map<RiskOfRain2Item, Integer> getRiskOfRain2Items() {
		return new LinkedHashMap<RiskOfRain2Item, Integer>(this.items);
	}

}
