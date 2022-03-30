package znick_.riskofrain2.api.mc.data.nbt;

import net.minecraft.nbt.NBTTagCompound;

/**
 * If a class implements {@code SavableToNBT}, it means that objects of that type can be 
 * written to and read from NBT. The reading and writing methods should be effectively 
 * static, meaning they do not depend on the instance of the class. The class must
 * also contain a nullary constructor. 
 * 
 * @author zNick_
 * 
 * @param <T> The type of object returned after being read from NBT, typically just the
 * implementing class.
 */
public interface SavableToNBT<T> {
	
	/**
	 * Writes this object to NBT. This method should be effectively static, meaning it does not
	 * depend on the instance of the class. 
	 * 
	 * @param nbt The {@code NBTTagCompound} to write to
	 * @param key The key to write it with. This method should always use this key without editing it.
	 * For example, if this is an integer, it should always start with {@code nbt.setInteger(key, ...)}.
	 * 
	 */
	public abstract void writeToNBT(NBTTagCompound nbt, String key);
	
	/**
	 * Reads this object from NBT.
	 * 
	 * @param nbt The {@code NBTTagCompound} to read from
	 * @param key The key to read with
	 * 
	 * @return An instance of this class read from the {@code NBTTagCompound} using the given key
	 */
	public abstract T readFromNBT(NBTTagCompound nbt, String key);
}
