package znick_.riskofrain2.api.mc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumChatFormatting;
import sun.misc.Unsafe;

/**
 * Class used for initializing custom {@link net.minecraft.item.EnumRarity EnumRarity} constants. Used for
 * giving items custom colors in the names.
 * 
 * @author zNick_
 */
public class CustomRarity {

	/**The rarity for red Risk of Rain 2 items*/
	public static final EnumRarity RED = createRarity(EnumChatFormatting.RED);
	/**The rarity for green Risk of Rain 2 items*/
	public static final EnumRarity GREEN = createRarity(EnumChatFormatting.GREEN);
	/**The rarity for lunar Risk of Rain 2 items*/
	public static final EnumRarity LUNAR = createRarity(EnumChatFormatting.BLUE);
	/**The rarity for equipment Risk of Rain 2 items*/
	public static final EnumRarity EQUIPMENT = createRarity(EnumChatFormatting.GOLD);
	/**The rarity for any Risk Of Rain 2 items with dark blue names.*/
	public static final EnumRarity DARK_BLUE = createRarity(EnumChatFormatting.DARK_BLUE);
	
	/**
	 * Creates a new {@link net.minecraft.item.EnumRarity EnumRarity} instance through various unsafe and
	 * highly inefficient methods. But that's what happens when you use enums for things that should be
	 * classes. Nice going Notch.
	 * 
	 * @param color The color of the rarity.
	 * @return The custom {@code EnumRarity} object.
	 */
	private static EnumRarity createRarity(EnumChatFormatting color) {
		// Attempts to dynamically generate a new EnumRarity constant
		try {
			// Gets the unsafe constructor
			Constructor<?> constructor = Unsafe.class.getDeclaredConstructors()[0];
			// Set the constructor accessable
		    constructor.setAccessible(true);
		    // Creates a new instance of the Unsafe class
		    Unsafe unsafe = (Unsafe) constructor.newInstance();
		    // Creates the rarity object
		    EnumRarity rarity = (EnumRarity) unsafe.allocateInstance(EnumRarity.class);
		    
		    // Get the color field
		    Field rarityField = EnumRarity.class.getDeclaredField("rarityColor");
		    // Make the field accessible
		    makeAccessible(rarityField);
		    // Sets the field to the EnumChatFormatting color
		    rarityField.set(rarity, color);
		    // Returns the rarity object
		    return rarity;
		} 
		
		// If an error occurs, throw a new one lmao eat shit checked exceptions
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Makes the given field accessible by stripping it of private and final modifiers.
	 * 
	 * @param field The field to make accessible
	 * @throws Exception If an exception occurs
	 */
	private static void makeAccessible(Field field) throws Exception {
	    field.setAccessible(true);
	    Field modifiersField = Field.class.getDeclaredField("modifiers");
	    modifiersField.setAccessible(true);
	    modifiersField.setInt(field, field.getModifiers() & ~ Modifier.FINAL);
	}
}
