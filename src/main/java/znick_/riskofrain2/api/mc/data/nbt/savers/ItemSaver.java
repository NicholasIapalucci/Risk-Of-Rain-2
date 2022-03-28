package znick_.riskofrain2.api.mc.data.nbt.savers;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

public class ItemSaver extends Saver<Item> {

	public ItemSaver(Item object) {
		super(object);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt, String key) {
		nbt.setInteger(key, Item.getIdFromItem(this.object));
	}

	@Override
	public Item readFromNBT(NBTTagCompound nbt, String key) {
		return Item.getItemById(nbt.getInteger(key));
	}

}
