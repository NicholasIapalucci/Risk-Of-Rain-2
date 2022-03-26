package znick_.riskofrain2.client.gui;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	
	private static int nextID = 0;
	
	private static final Map<Integer, Class<? extends Gui>> GUIS = new HashMap<>();
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		try {return GUIS.get(ID).getConstructor(int.class, int.class, int.class).newInstance(x, y, z);} 
		catch (Exception e) {throw new RuntimeException(e);}
	}
	
	public static int getNextID(Class<? extends Gui> gui) {
		int id = nextID++;
		GUIS.put(id, gui);
		return id;
	}
}
