package znick_.riskofrain2.api.mc.data.nbt;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import net.minecraft.nbt.NBTTagCompound;
import scala.actors.threadpool.Arrays;
import znick_.riskofrain2.api.mc.data.nbt.savers.Saver;
import znick_.riskofrain2.util.helper.ReflectionHelper;

/**
 * Helper class of static methods for reading and writing to NBT more conveniently.
 * 
 * @author zNick_
 */
public class NBTHelper {

	private static final boolean WRITING_DEBUG = false;
	private static final boolean READING_DEBUG = false;
	
	/**
	 * Writes all fields in this class to NBT if they are able to. Fields can be written to NBT iff
	 * either there is a method in the {@code NBTTagCompound} class for it by default (such as
	 * {@link NBTTagCompound#setInteger(String, int)} or the field implements {@link SavableToNBT}.
	 * These fields can later be read using {@link #readFieldsFromNBT(NBTTagCompound, Object)}.
	 * Alternatively, fields that are annotated with {@link #SavableWith} can be written as well.
	 * Also, arrays and collections of such fields can be written and read.
	 * <br><br>
	 * However, it is important to note that certiain complex data structures aren't yet supported
	 * to be written/read from NBT. Specifically, a "collection of collection" cannot be read - or
	 * more generally, any field that has a generic type argument which is in itself a class with
	 * another generic type argument.
	 * 
	 * @param nbt The {@code NBTTagCompound} to write to
	 * @param object The object that has the fields to write
	 */
	public static void writeFieldsToNBT(NBTTagCompound nbt, Object object) {
		for (Field field : object.getClass().getDeclaredFields()) {
			if (WRITING_DEBUG) {
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("Attempting to write field \"" + field.getName() + "\" to NBT...");
			}
			
			writeFieldToNBT(nbt, field, object);
			
			if (WRITING_DEBUG) {
				System.out.println("--------------------------------------------------------------------------");
				System.out.println();
			}
		}
	}
	
	/**
	 * Writes the given field to NBT if possible. 
	 * 
	 * @param nbt The {@code NBTTagCompound} to write to
	 * @param field The field to write
	 * @param object The object that owns the field
	 */
	private static void writeFieldToNBT(NBTTagCompound nbt, Field field, Object object) {

		try {
			field.setAccessible(true);
			
			if (Modifier.isStatic(field.getModifiers())) {
				if (WRITING_DEBUG) System.out.println(field.getName() + " is static. Skipping it...");
				return;
			}
			
			if (field.isAnnotationPresent(Unsaved.class)) {
				if (WRITING_DEBUG) System.out.println(field.getName() + " has the @Unsaved annotation. Skipping it...");
				return;
			}
			
			Object value = field.get(object);
			
			if (value == null) {
				if (WRITING_DEBUG) System.out.println("field value is null. Skipping it...");
				return;
			}
			
			// Write the field
			writeObjectToNBT(nbt, field.getName(), value, field.getAnnotations());
			
			if (WRITING_DEBUG) System.out.println("Finished writing " + field.getName() + " to NBT");
		} 
					
		catch(Exception e) {
			if (WRITING_DEBUG) System.out.println("Error writing field " + field.getName() + " to NBT");
		}
	}
	
	/**
	 * Attempts to write the given object to NBT. 
	 * 
	 * @param nbt The {@code NBTTagCompound} to write to.
	 * @param key The key to write it with
	 * @param value The object to write
	 * @param annotations The annotations on the field that the object came from.
	 */
	private static void writeObjectToNBT(NBTTagCompound nbt, String key, Object value, Annotation[] annotations) {
		if (WRITING_DEBUG) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("Attempting to write " + key + " with annotations " + Arrays.toString(annotations));
		}
		
		// Write the field to NBT if it's primitive
		if (WRITING_DEBUG) System.out.println("Checking if it's writable by default...");
		if (existsDefaultMethodFor(value)) {
			if (WRITING_DEBUG) System.out.println("It is! Attempting to write using default methods...");
			writePrimitiveField(nbt, key, value);
			return;
		}
				
		// Write the field if it's an enum
		if (WRITING_DEBUG) System.out.println("It's not! Checking if it's an enum constant...");
		if (value instanceof Enum) {
			if (WRITING_DEBUG) System.out.println("It is! Attempting to write it as an enum constant...");
			writeEnumObjectToNBT(nbt, key, (Enum) value);
			return;
		}
		
		// Write the field if it's a collection
		if (WRITING_DEBUG) System.out.println("It's not! Checking if it's a collection...");
		if (value instanceof Collection) {
			if (WRITING_DEBUG) System.out.println("It is! Attempting to write it as a collection...");
			writeCollectionToNBT(nbt, key, (Collection) value, annotations);
			return;
		}
		
		// Write the field if it's a map
		if (WRITING_DEBUG) System.out.println("It's not! Checking if it's a map...");
		if (value instanceof Map) {
			if (WRITING_DEBUG) System.out.println("It is! Attempting to write it as a map...");
			writeMapToNBT(nbt, key, (Map) value, annotations);
			return;
		}
		
		// Write the field to NBT if it implements SavableToNBT
		if (WRITING_DEBUG) System.out.println("It's not! Checking if it implements SavableToNBT...");
		if (value instanceof SavableToNBT) {
			if (WRITING_DEBUG) System.out.println("It is! Attempting to write it with SavableToNBT...");
			((SavableToNBT) value).writeToNBT(nbt, key);
			return;
		}
		
		// Loop through annotations
		if (WRITING_DEBUG) System.out.println("It doesn't! Checking for any SavableWith annotations...");
		for (Annotation annotation : annotations) {	
			
			// Save objects savable with the SavableWith annotation
			if (annotation instanceof SavableWith) {
				writeObjectToNBTWithSaver(nbt, key, value, ((SavableWith) annotation).value());
				return;
			}	
			
			// Map keys and values (wont have regular maps; theyre already covered above)
			if (annotation instanceof SavableMap) {
				SavableMap savMap = (SavableMap) annotation;
				boolean isKey = key.endsWith("key");
				writeObjectToNBTWithSaver(nbt, key, value, isKey? savMap.keySaver() : savMap.valueSaver());
				return;
			}
		}
		
		if (WRITING_DEBUG) System.out.println("No SavableWith annotations! Out of methods to save the object. Skipping.");
	}
	
	/**
	 * Writes an object to NBT with the given {@link Saver} class.
	 * 
	 * @param nbt The {@code NBTTagCompound}
	 * @param key The key to store the mapping with
	 * @param object The object to write
	 * @param saverClass The {@code Saver} class to save it with
	 */
	private static void writeObjectToNBTWithSaver(NBTTagCompound nbt, String key, Object object, Class<? extends Saver> saverClass) {
		if (WRITING_DEBUG) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("Attempting to write " + key + " to NBT with a Saver class...");
		}
		
		// If the saver class isn't defined, use the default
		if (saverClass == Saver.class) saverClass = Saver.getDefaultSaver(object.getClass());
		
		try {
			Constructor<? extends Saver> constructor = ConstructorUtils.getMatchingAccessibleConstructor(saverClass, object.getClass());
			if (WRITING_DEBUG) {
				if (constructor == null) System.out.println("Error! No valid constructor found!");
				else System.out.println("Found valid constructor for " + saverClass.getSimpleName());
			}
			if (WRITING_DEBUG) System.out.println("Creating saver instance");
			Saver saver = constructor.newInstance(object);
			if (WRITING_DEBUG) System.out.println("Writing to NBT...");
			saver.writeToNBT(nbt, key);
			if (WRITING_DEBUG) {
				System.out.println("Successfully wrote " + key + " to NBT");
				System.out.println("---------------------------------------------------------------------------------");
			}
			
		}
		
		catch(Exception e) {
			if (WRITING_DEBUG) {
				System.out.println("Error writing " + key + " to NBT: " + e.getClass().getSimpleName());
				System.out.println("---------------------------------------------------------------------------------");
			}
		}
	}
	
	/**
	 * Writes the given {@code enum} object to NBT.
	 * 
	 * @param nbt The {@code NBTTagCompound} to write to
	 * @param key The key to write with
	 * @param object The object to write
	 */
	private static void writeEnumObjectToNBT(NBTTagCompound nbt, String key, Enum object) {
		if (WRITING_DEBUG) System.out.println("Writing enum constant to NBT...");
		nbt.setInteger(key, object.ordinal() + 1);
		if (WRITING_DEBUG) System.out.println("Enum constant succesfully written.");
	}
	
	/**
	 * Attempts to write a single map entry to NBT.
	 * 
	 * @param nbt The {@code NBTTagCompound} to write to
	 * @param key The key to write with
	 * @param entry The map entry
	 * @param annotation A {@code SavableMap} annotation, or {@code null} if none is present
	 * 
	 * @param <K> The type of keys in the map
	 * @param <V> The type of values in the map
	 */
	private static <K, V> void writeMapEntryToNBT(NBTTagCompound nbt, String key, Map.Entry<K, V> entry, Annotation[] annotations) {;
		if (WRITING_DEBUG) System.out.println("Writing entry number " + key.charAt(key.length() - 1));
		writeObjectToNBT(nbt, key + "_key", entry.getKey(), annotations);
		writeObjectToNBT(nbt, key + "_value", entry.getValue(), annotations);
	}
	
	/**
	 * Attempts to write a map to NBT. This is only possible if the keys and values of the map can
	 * be written to NBT, which is determined through the same criteria as in 
	 * {@link #writeFieldsToNBT(NBTTagCompound, Object)}. However, instead of the {@code SavableWith}
	 * annotation, maps can be annotated with the {@link SavableMap} annotation to allow reading
	 * and writing for objects that are generally non-savable.
	 * 
	 * @param nbt The {@code NBTTagCompound} to write to
	 * @param field The map field to write to NBT
	 * @param map The map from the field
	 */
	private static <K, V> void writeMapToNBT(NBTTagCompound nbt, String key, Map<K, V> map, Annotation[] annotations) {
		if (WRITING_DEBUG) System.out.println("Writing " + key + " to NBT as map...");
		int i = 0;
		for (Map.Entry<K, V> entry : map.entrySet()) {
			writeMapEntryToNBT(nbt, key + "_entry_" + i, entry, annotations);
			i++;
		}
	}
	
	private static <T> void writeCollectionToNBT(NBTTagCompound nbt, String key, Collection<T> collection, Annotation[] annotations) {
		if (WRITING_DEBUG) System.out.println("Writing " + key + " to NBT as a collection...");
		int i = 0;
		for (T obj : collection) {
			writeObjectToNBT(nbt, key + "_element_" + i, obj, annotations);
			i++;
		}
	}
	
	/**
	 * Writes a primitive (or wrapper) field to NBT. 
	 * 
	 * @param nbt The {@code NBTTagCompound} to write to
	 * @param key The key to use in the writing
	 * @param object The object to write
	 * 
	 * @return whether or not the writing was successful
	 */
	private static boolean writePrimitiveField(NBTTagCompound nbt, String key, Object object) {
		
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
		
		if (is(object, int[].class)) {
			nbt.setIntArray(key, (int[]) object);
			return true;
		}
		
		if (is(object, byte[].class)) {
			nbt.setByteArray(key, (byte[]) object);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Reads all fields in this class to NBT if they are able to. Fields can be read to NBT iff
	 * either there is a method in the {@code NBTTagCompound} class for it by default (such as
	 * {@link NBTTagCompound#getInteger(String)} or the field implements {@link SavableToNBT}.
	 * These fields must be written beforehand using {@link #writeFieldsToNBT(NBTTagCompound, Object)}.
	 * Alternatively, fields that are annotated with {@link SavableWith} can be read as well.
	 * 
	 * @param nbt The {@code NBTTagCompound} to read from
	 * @param object The object that has the fields to read
	 */
	public static void readFieldsFromNBT(NBTTagCompound nbt, Object object) {
		for (Field field : object.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				
				if (READING_DEBUG) {
					System.out.println("--------------------------------------------------------------------------");
					System.out.println("Attempting to read field \"" + field.getName() + "\" from NBT...");
				}
				
				// Skip static fields
				if (Modifier.isStatic(field.getModifiers())) {
					if (READING_DEBUG) System.out.println(field.getName() + " is static. Skipping it...");
					continue;
				}
				
				// Skip @Unsaved fields
				if (field.isAnnotationPresent(Unsaved.class)) {
					if (READING_DEBUG) System.out.println(field.getName() + " has the @Unsaved annotation. Skipping it...");
					continue;
				}
				
				// Read the field from NBT
				Object obj = readObjectFromNBT(nbt, field.getName(), field.getType(), ReflectionHelper.getFieldGenerics(field), field.getAnnotations());
				field.set(object, obj);
			} 
			
			catch(Exception e) {
				if (READING_DEBUG) System.out.println("Unable to read field " + field.getName() + " from NBT: " + e.getMessage());
			}
		}
	}
	
	private static <T> T readObjectFromNBT(NBTTagCompound nbt, String key, Class<T> objectClass, Class[] genericTypeArguments, Annotation[] annotations) {
		if (READING_DEBUG) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("Attempting to read object " + key + " from NBT...");
		}
		
		// Read the field if it's primitive
		if (READING_DEBUG) System.out.println("Checking if it's readable by default...");
		if (existsDefaultMethodForR(objectClass)) {
			if (READING_DEBUG) System.out.println("It is! Reading using default methods...");
			return (T) readPrimitiveObject(nbt, key, objectClass);
		}
		
		// Read the field if it's an enum object
		if (READING_DEBUG) System.out.println("It's not! Checking if it's an enum constant...");
		if (Enum.class.isAssignableFrom(objectClass)) {
			if (READING_DEBUG) System.out.println("It is! Reading as an enum constant...");
			return (T) readEnumObject(nbt, key, (Class<? extends Enum>) objectClass);
		}
				
		// Read the field from NBT if it implements SavableToNBT
		if (READING_DEBUG) System.out.println("It's not! Checking if it implements SavableToNBT...");
		if (SavableToNBT.class.isAssignableFrom(objectClass)) {
			if (READING_DEBUG) System.out.println("It is! Reading using SavableToNBT...");
			return (T) readSavableToNBTObject(nbt, key, (Class<? extends SavableToNBT>) objectClass);
		}
				
		// Read the field from NBT if it's a map
		if (READING_DEBUG) System.out.println("It doesn't! Checking if it's a map...");
		if (Map.class.isAssignableFrom(objectClass)) {
			if (READING_DEBUG) System.out.println("It is! Reading as a map...");
			return (T) readMapFromNBT(nbt, key, (Class<? extends Map>) objectClass, genericTypeArguments[0], genericTypeArguments[1], annotations);
		}
				
		// Read the field from NBT if it's a Collection
		if (READING_DEBUG) System.out.println("It's not! Checking if it's a collection...");
		if (Collection.class.isAssignableFrom(objectClass)) {
			if (READING_DEBUG) System.out.println("It is! Reading as a collection...");
			return (T) readCollectionFromNBT(nbt, key, (Class<Collection<?>>) objectClass, genericTypeArguments[0], annotations);
		}
				
		// Loop through annotations
		if (READING_DEBUG) System.out.println("It's not! Checking annotations...");
		for (Annotation annotation : annotations) {
									
			// Load objects with the SavableWith annotation
			if (annotation instanceof SavableWith) {
				return readObjectFromNBTWithSaver(nbt, key, objectClass, ((SavableWith) annotation).value());
			}
			
			// Load map entry key/values (maps themselves will not appear here; they are already handled above)
			if (annotation instanceof SavableMap) {
				if (READING_DEBUG) System.out.println("SavableMap annotation present. Proceeding...");
				SavableMap savMap = (SavableMap) annotation;
				boolean isKey = key.endsWith("key");
				return readObjectFromNBTWithSaver(nbt, key, objectClass, isKey? savMap.keySaver() : savMap.valueSaver());
			}
							
		}
		
		// If none of the above apply, the value cannot be read from NBT and return null
		return null;
	}
	
	private static <T extends SavableToNBT> T readSavableToNBTObject(NBTTagCompound nbt, String key, Class<T> objectClass) {
		try {
			return (T) objectClass.newInstance().readFromNBT(nbt, key);
		}
		
		catch(Exception e) {
			if (READING_DEBUG) System.out.println("Error reading SavableToNBT object from NBT");
			return null;
		}
	}
	
	/**
	 * Returns whether there exists a default method in the {@code NBTTagCompound} class for
	 * writing this field to NBT.
	 * 
	 * @param object The object to check
	 * 
	 * @return true iff the object can be written with a default method 
	 */
	private static boolean existsDefaultMethodFor(Object object) {
		return writePrimitiveField(new NBTTagCompound(), "dummy_text", object);
	}
	
	private static boolean existsDefaultMethodForR(Class object) {
		return  isR(object, int.class,     Integer.class) ||
				isR(object, double.class,  Double.class)  ||
				isR(object, long.class,    Long.class)    ||
				isR(object, String.class)                 ||
				isR(object, byte.class,    Byte.class)    ||
				isR(object, boolean.class, Boolean.class) ||
				isR(object, short.class,   Short.class)   ||
				isR(object, float.class,   Float.class)   ||
				isR(object, int[].class)                  ||
				isR(object, byte[].class);
	}
	
	/**
	 * Returns whether or not the given object is an instance of any of the given classes.
	 * Quick and concise shorthand method for low-code checking.
	 * 
	 * @param object The object to check
	 * @param clazzs The classes to check
	 * 
	 * @return Whether or not the object is an instance of any of the classes
	 */
	private static boolean is(Object object, Class... clazzs) {
		for (Class clazz : clazzs) if (object.getClass() == clazz) return true;
		return false;
	}
	
	private static boolean isR(Class object, Class... clazzs) {
		for (Class clazz : clazzs) if (object == clazz) return true;
		return false;
	}
	
	/**
	 * Reads a primitive field from NBT.
	 * 
	 * @param nbt The {@code NBTTagCompound to read from
	 * @param key The key to use  
	 * @param object The object to read to  
	 * 
	 * @param <T> The type of the object  
	 * 
	 * @return the value of the read field, or {@code null} if no such mapping exists  
	 */
	private static <T> T readPrimitiveObject(NBTTagCompound nbt, String key, Class<T> objectClass) {
		T defaultObject = (T) Array.get(Array.newInstance(objectClass, 1), 0);
		T readObject = null;
		
		if (isR(objectClass, int.class, Integer.class)) readObject = (T) (Integer) nbt.getInteger(key);
		else if (isR(objectClass, double.class, Double.class)) readObject = (T) (Double) nbt.getDouble(key);
		else if (isR(objectClass, long.class, Long.class)) readObject = (T) (Long) nbt.getLong(key);
		else if (isR(objectClass, String.class)) readObject = (T) nbt.getString(key);
		else if (isR(objectClass, byte.class, Byte.class)) readObject = (T) (Byte) nbt.getByte(key);
		else if (isR(objectClass, boolean.class, Boolean.class)) readObject = (T) (Boolean) nbt.getBoolean(key);
		else if (isR(objectClass, short.class, Short.class)) readObject = (T) (Short) nbt.getShort(key);
		else if (isR(objectClass, float.class, Float.class)) readObject = (T) (Float) nbt.getFloat(key);
		else if (isR(objectClass, int[].class)) readObject = (T) nbt.getIntArray(key);
		else if (isR(objectClass, byte[].class)) readObject = (T) nbt.getByteArray(key);
		
		return readObject;	
	}
	
	/**
	 * Reads an enum constant from NBT.
	 * 
	 * @param nbt The {@code NBTTagCompound}
	 * @param key The key to read with
	 * @param object The object to read
	 * 
	 * @return The object read from NBT.
	 */
	private static <T extends Enum<T>> T readEnumObject(NBTTagCompound nbt, String key, Class<T> clazz) {
		try {
			return (T) clazz.getEnumConstants()[nbt.getInteger(key) - 1];
		} 
		
		catch(Exception e) {
			if (READING_DEBUG) System.out.println("Error reading enum constant from NBT");
			return null;
		}
	}
	
	private static <T> Collection<T> readCollectionFromNBT(NBTTagCompound nbt, String key, Class<Collection<?>> objectClass, Class<T> genericClass, Annotation[] annotations) {
		Collection<T> collection = null;
		try {
			collection = (Collection<T>) objectClass.newInstance();
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				T obj = (T) readObjectFromNBT(nbt, key + "_element_" + i, genericClass, new Class<?>[0], annotations);
				if (obj == null) return collection;
				collection.add(obj);
			}
			return null;
		}
		
		catch(Exception e) {
			return collection;
		}
	}
	
	private static <K, V> Map<K, V> readMapFromNBT(NBTTagCompound nbt, String key, Class<? extends Map> mapClass, Class<K> keyClass, Class<V> valueClass, Annotation[] annotations) {
		if (READING_DEBUG) System.out.println("Attempting to read map " + key + " from NBT...");
		try {
			Map<K, V> map = new LinkedHashMap<K, V>();
			if (READING_DEBUG) System.out.println("Iterating over map...");
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				Map.Entry<K, V> entry = readMapEntryFromNBT(nbt, key + "_entry_" + i, keyClass, valueClass, annotations);
				K entryKey = entry.getKey();
				V entryValue = entry.getValue();
				if (entryKey == null && entryValue == null) {
					if (READING_DEBUG) System.out.println("Iteration complete after " + i + " iterations.");
					return map;
				}
				map.put(entryKey, entryValue);
			}
			return null;
		}
		
		catch(Exception e) {
			if (READING_DEBUG) System.out.println("Error reading map from NBT");
			return null;
		}
	}
	
	private static <K, V> Map.Entry<K, V> readMapEntryFromNBT(NBTTagCompound nbt, String key, Class<K> keyClass, Class<V> valueClass, Annotation[] annotations) {
		if (READING_DEBUG) System.out.println("Reading map entry " + key + " from NBT...");
		K theKey = readObjectFromNBT(nbt, key + "_key", keyClass, new Class[0], annotations);
		V theValue = readObjectFromNBT(nbt, key + "_value", valueClass, new Class[0], annotations);	
		return new AbstractMap.SimpleEntry<K, V>(theKey, theValue);
	}
	
	private static <T> T readObjectFromNBTWithSaver(NBTTagCompound nbt, String key, Class<T> clazz, Class<? extends Saver> saverClass) {
		if (READING_DEBUG) System.out.println("Reading " + key + " with Saver...");
		
		// If the saver class isn't defined, use the default
		if (saverClass == Saver.class) {
			if (READING_DEBUG) System.out.println("No Saver provided. Defaulting to default...");
			saverClass = Saver.getDefaultSaver(clazz);
		}
		T readObject = null;		
		
		try {
			if (READING_DEBUG) System.out.println("Creating new Saver instance...");
			Saver<T> saver = saverClass.newInstance();
			if (READING_DEBUG) System.out.println("Instance created! reading from NBT...");
			readObject = saver.readFromNBT(nbt, key);
			if (READING_DEBUG) System.out.println("Reading complete! Value is " + readObject);
		}
				
		catch(Exception e) {
			if (READING_DEBUG) System.out.println("Error reading object from NBT: " + e.getMessage());
		}
		
		return readObject;
	}
}
