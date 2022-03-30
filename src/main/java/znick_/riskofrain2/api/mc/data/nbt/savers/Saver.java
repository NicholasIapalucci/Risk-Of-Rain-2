package znick_.riskofrain2.api.mc.data.nbt.savers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.item.Item;
import znick_.riskofrain2.api.mc.data.nbt.SavableToNBT;

/**
 * {@code Saver} classes are classes that demonstrate how to make any object 
 * act as a {@link SavableToNBT} object. This is used when a class can't directly
 * implement {@code SavableToNBT}, usually because it is part of Java's API or
 * the Minecraft API, neither of which can be edited. If that is not the case,
 * simply implement {@code SavableToNBT} instead.
 * <br><br>
 * Note that all {@code Saver} classes must have a nullary constructor to be written to
 * and read from NBT properly.
 * <br><br>
 * Several subclasses for this are written by default, though this class also exists
 * so that any addons to this mod can use it as well. For a simple example on how to
 * use this class, see {@link UUIDSaver} or {@link ItemSaver}.
 * 
 * @author zNick_
 *
 * @param <T> The type of object that can be written to and read from NBT.
 */
public abstract class Saver<T> implements SavableToNBT<T> {

	/**The map of classes to their default {@code Saver} classes.*/
	private static final Map<Class<?>, Class<? extends Saver<?>>> DEFAULT_SAVERS = new HashMap<>();
	
	static {
		setDefaultSaver(UUID.class, UUIDSaver.class);
		setDefaultSaver(Item.class, ItemSaver.class);
	}
	
	/**The object to read and write to NBT*/
	protected final T object;
	
	/**
	 * Creates a new {@code Saver} with the given object. If there is not already a default saver
	 * for this object type, this class will be set as the default using {@link #setDefaultSaver(Class, Class)}.
	 * 
	 * @param object The object to read and write to NBT.
	 */
	public Saver(T object) {
		this.object = object;
		if (!DEFAULT_SAVERS.containsKey(object.getClass())) setDefaultSaver((Class<T>) object.getClass(), (Class<? extends Saver<T>>) this.getClass());
	}
	
	public Saver() {
		this.object = null;
	}
	
	/**
	 * Sets a {@code Saver} class as the default saver for some object type.
	 * 
	 * @param clazz The class of the object to save
	 * @param saverClazz The {@code Saver} class to make the default
	 */
	public static <T> void setDefaultSaver(Class<T> clazz, Class<? extends Saver<T>> saverClazz) {
		DEFAULT_SAVERS.put(clazz, saverClazz);	
	}

	/**
	 * Returns the default {@code Saver} class fo the given object.
	 * 
	 * @param clazz The class of the object to get the saver for
	 * 
	 * @return The default {@code Saver} class if one exists, or {@code null} otherwise.
	 */
	public static <T> Class<? extends Saver<T>> getDefaultSaver(Class<T> clazz) {
		return (Class<? extends Saver<T>>) DEFAULT_SAVERS.get(clazz);
	}
	
	private Class<? extends Saver> findDefaultSaverClass(Object object) {
		Class<? extends Saver> saverClass = null;
		Class superClass = object.getClass();
		while (superClass != Object.class) {
			saverClass = Saver.getDefaultSaver(superClass);
			if (saverClass != null) break;
		}
		return saverClass;
	}
}
