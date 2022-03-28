package znick_.riskofrain2.api.mc.data.nbt.savers;

import znick_.riskofrain2.api.mc.data.nbt.SavableToNBT;

/**
 * {@code Saver} classes are classes that demonstrate how to make any object 
 * act as a {@link SavableToNBT} object. This is used when a class can't directly
 * implement {@code SavableToNBT}, usually because it is part of Java's API or
 * the Minecraft API, neither of which can be edited. If that is not the case,
 * simply implement {@code SavableToNBT} instead.
 * <br><br>
 * Several subclasses for this are written by default, though this class also exists
 * so that any addons to this mod can use it as well. For a simple example on how to
 * use this class, see {@link UUIDSaver}.
 * 
 * @author zNick_
 *
 * @param <T> The type of object that can be written to and read from NBT.
 */
public abstract class Saver<T> implements SavableToNBT<T> {

	protected final T object;
	
	public Saver(T object) {
		this.object = object;
	}
}
