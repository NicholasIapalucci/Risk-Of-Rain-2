package znick_.riskofrain2.api.mc.data.nbt;

import net.minecraft.nbt.NBTTagCompound;

/**
 * If a class implements {@code SavableToNBT}, it means that objects of that type can be 
 * written to and read from NBT. 
 * 
 * @author zNick_
 * 
 * @param <T> The type of object returned after being read from NBT, typically just the
 * implementing class.
 */
public interface SavableToNBT<T> {
	
	public abstract void writeToNBT(NBTTagCompound nbt, String key);
	public abstract T readFromNBT(NBTTagCompound nbt, String key);
}
