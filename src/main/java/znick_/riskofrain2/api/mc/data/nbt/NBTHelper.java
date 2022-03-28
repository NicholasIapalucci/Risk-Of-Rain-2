package znick_.riskofrain2.api.mc.data.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import net.minecraft.nbt.NBTTagCompound;
import znick_.riskofrain2.api.mc.data.nbt.savers.Saver;

public class NBTHelper {

	/**
	 * Writes all fields in this class to NBT if they are able to. Fields can be written to NBT iff
	 * either there is a method in the {@code NBTTagCompound} class for it by default (such as
	 * {@link NBTTagCompound#setInteger(String, int)} or the field implements {@link SavableToNBT}.
	 * These fields can later be read using {@link #readFieldsFromNBT(NBTTagCompound, Object)}.
	 * Alternatively, fields that are annotated with {@link #SavableWith} can be written as well.
	 * Also, arrays and collections of such fields can be written and read.
	 * 
	 * @param nbt The {@code NBTTagCompound} to write to
	 * @param object The object that has the fields to write
	 */
	public static void writeFieldsToNBT(NBTTagCompound nbt, Object object) {
		
		// Loop through all fields in the class
		for (Field field : object.getClass().getDeclaredFields()) {
			
			// Ignore static fields
			if (Modifier.isStatic(field.getModifiers())) continue;
			
			try {
				field.setAccessible(true);
				Object value = field.get(object);
				
				// Ignore null values
				if (value == null) continue;
				
				// If the field isn't specified to be unsaved, continue
				if (!field.isAnnotationPresent(Unsaved.class)) {
					
					// Write the field to NBT if it's primitive
					if (existsDefaultMethodFor(value)) writePrimitiveField(nbt, field.getName(), value);
					
					// Write the field to NBT if it implements SavableToNBT
					else if (value instanceof SavableToNBT) ((SavableToNBT) value).writeToNBT(nbt, field.getName());
					
					// Write the field to NBT if it has a saver class
					else if (field.isAnnotationPresent(SavableWith.class)) {
						Class<? extends Saver> saverClass = field.getAnnotation(SavableWith.class).saver();
						Constructor<? extends Saver> constructor = ConstructorUtils.getMatchingAccessibleConstructor(saverClass, field.getType());
						Saver saver = constructor.newInstance(value);
						saver.writeToNBT(nbt, field.getName());
					}
					
					// Otherwise, don't write the field
				}
			} 
			
			catch(Exception e) {
				System.out.println("Unable to write field " + field + " to NBT");
			}
		}
	}
	
	/**s
	 * Reads all fields in this class to NBT if they are able to. Fields can be read to NBT iff
	 * either there is a method in the {@code NBTTagCompound} class for it by default (such as
	 * {@link NBTTagCompound#getInteger(String)} or the field implements {@link SavableToNBT}.
	 * These fields must be written beforehand using {@link #writeFieldsToNBT(NBTTagCompound, Object)}.
	 * Alternatively, fields that are annotated with {@link #SavableWith} can be read as well.
	 * 
	 * @param nbt The {@code NBTTagCompound} to read from
	 * @param object The object that has the fields to read
	 */
	public static void readFieldsFromNBT(NBTTagCompound nbt, Object object) {
		for (Field field : object.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				Object value = field.get(object);
				if (!field.isAnnotationPresent(Unsaved.class)) {
					
					// Write the field to NBT if it's primitive
					if (existsDefaultMethodFor(value)) field.set(object, readPrimitiveField(nbt, field.getName(), value));
					
					// Write the field to NBT if it implements SavableToNBT
					else if (value instanceof SavableToNBT) field.set(object, ((SavableToNBT) value).readFromNBT(nbt, field.getName()));
					
					// Read the field from NBT if it has a saver class
					else if (field.isAnnotationPresent(SavableWith.class)) {
						Class<? extends Saver> saverClass = field.getAnnotation(SavableWith.class).saver();
						Constructor<? extends Saver> constructor = ConstructorUtils.getMatchingAccessibleConstructor(saverClass, field.getType());
						Saver saver = constructor.newInstance(value);
						field.set(object, saver.readFromNBT(nbt, field.getName()));
					}
				}
			} 
			
			catch(Exception e) {
				System.out.println("Unable to read field " + field + " from NBT");
			}
		}
	}
	
	public static boolean existsDefaultMethodFor(Object object) {
		return object.getClass() == int.class || object.getClass() == Integer.class;
	}
	
	private static boolean writePrimitiveField(NBTTagCompound nbt, String key, Object object) {
		//TODO: add other cases lmao
		
		if (is(object, int.class, Integer.class)) {
			nbt.setInteger(key, (int) object);
			return true;
		}
		
		if (is(object, double.class, Double.class)) {
			nbt.setDouble(key, (double) object);
			return true;
		}
		
		if (is(object, long.class, Long.class)) {
			nbt.setLong(key, (long) object);
			return true;
		}
		
		if (is(object, float.class, Float.class)) {
			nbt.setFloat(key, (float) object);
			return true;
		}
		
		if (is(object, String.class)) {
			nbt.setString(key, (String) object);
			return true;
		}
		
		if (is(object, boolean.class, Boolean.class)) {
			nbt.setBoolean(key, (boolean) object);
			return true;
		}
		
		if (is(object, byte.class, Byte.class)) {
			nbt.setByte(key, (byte) object);
			return true;
		}
		
		if (is(object, short.class, Short.class)) {
			nbt.setShort(key, (short) object);
			return true;
		}
		
		if (is(object, int[].class, Integer[].class)) {
			nbt.setIntArray(key, (int[]) object);
			return true;
		}
		
		if (is(object, byte[].class, Byte[].class)) {
			nbt.setByteArray(key, (byte[]) object);
			return true;
		}
		
		return false;
	}
	
	private static boolean is(Object object, Class... clazzs) {
		for (Class clazz : clazzs) if (object.getClass() == clazz) return true;
		return false;
	}
	
	private static Object readPrimitiveField(NBTTagCompound nbt, String key, Object object) {
		if (object.getClass() == int.class || object.getClass() == Integer.class) return nbt.getInteger(key);
		return null;
	}
}
