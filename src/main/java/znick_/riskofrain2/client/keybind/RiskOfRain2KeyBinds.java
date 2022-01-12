package znick_.riskofrain2.client.keybind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public enum RiskOfRain2KeyBinds {
	
	PRIMARY(Keyboard.KEY_X, "key.primary.desc", "key.riskofrain2.category"),
	SECONDARY(Keyboard.KEY_Z, "key.secondary.desc", "key.riskofrain2.category"),
	UTILITY(Keyboard.KEY_LMENU, "key.utility.desc", "key.riskofrain2.category"),
	SPECIAL(Keyboard.KEY_R, "key.special.desc", "key.riskofrain2.category"),
	ACTIVE(Keyboard.KEY_C, "key.active.desc", "key.riskofrain2.category");
	
	private int bind;
	private KeyBinding kb;
	
	/**
	 * Creates a new keybinding.
	 * 
	 * @param bind The key to use
	 * @param desc The description of the keybinding
	 * @param category The category of the keybinding
	 */
	private RiskOfRain2KeyBinds(int bind, String desc, String category) {
		this.bind = bind;
		this.kb = new KeyBinding(desc, bind, category);
	}
	
	/**
	 * Returns the {@code KeyBinding} object associated with this keybinding.
	 */
	public KeyBinding getKeyBinding() {
		return this.kb;
	}
	
	/**
	 * Returns the key that must be pressed to activate the binding.
	 */
	public int getKeyID() {
		return this.bind;
	}
	
	/**
	 * Registers the keybindings into the game.
	 */
	public static void registerKeyBinds() {
		ClientRegistry.registerKeyBinding(PRIMARY.getKeyBinding());
		ClientRegistry.registerKeyBinding(SECONDARY.getKeyBinding());
		ClientRegistry.registerKeyBinding(UTILITY.getKeyBinding());
		ClientRegistry.registerKeyBinding(SPECIAL.getKeyBinding());
		ClientRegistry.registerKeyBinding(ACTIVE.getKeyBinding());
	}
}
