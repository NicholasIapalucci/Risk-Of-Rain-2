package znick_.riskofrain2.client.keybind;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.survivor.ability.AbilityType;
import znick_.riskofrain2.client.gui.logbook.LogbookGui;

public enum RiskOfRain2KeyBinds {
	
	PRIMARY(new AbilityAction(AbilityType.PRIMARY), Keyboard.KEY_X, "key.primary.desc"),
	SECONDARY(new AbilityAction(AbilityType.SECONDARY), Keyboard.KEY_Z, "key.secondary.desc"),
	UTILITY(new AbilityAction(AbilityType.UTILITY), Keyboard.KEY_LMENU, "key.utility.desc"),
	SPECIAL(new AbilityAction(AbilityType.SPECIAL), Keyboard.KEY_R, "key.special.desc"),
	ACTIVE(new AbilityAction(AbilityType.EQUIPMENT), Keyboard.KEY_C, "key.active.desc"),
	
	LOGBOOK(new PlayerAction() {
		@Override
		public void run(PlayerData player) {
			player.getEntity().openGui(RiskOfRain2Mod.instance, LogbookGui.GUI_ID, player.getWorld(), (int) player.x(), (int) player.y(), (int) player.z());
		}
	}, Keyboard.KEY_L, "key.logbook.desc");
	
	private final PlayerAction action;
	private final int bind;
	private final KeyBinding kb;
	
	/**
	 * Creates a new keybinding.
	 * 
	 * @param bind The key to use
	 * @param desc The description of the keybinding
	 * @param category The category of the keybinding
	 */
	private RiskOfRain2KeyBinds(PlayerAction action, int bind, String desc) {
		this.action = action;
		this.bind = bind;
		this.kb = new KeyBinding(desc, bind, "key.riskofrain2.category");
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
		for (RiskOfRain2KeyBinds bind : RiskOfRain2KeyBinds.values()) {
			ClientRegistry.registerKeyBinding(bind.getKeyBinding());
		}
	}

	public void activate(EntityPlayer player) {
		this.action.run(PlayerData.get(player));
	}
}
