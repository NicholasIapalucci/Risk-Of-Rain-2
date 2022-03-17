package znick_.riskofrain2.util.helper;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import znick_.riskofrain2.item.ror.list.equipment.RiskOfRain2Equipment;

public class MinecraftHelper {
	
	/**
	 * Takes the instance of a player on the client and converts it to the player on the server.
	 * 
	 * @param player The client player to convert to server
	 * 
	 * @author diesieben07
	 * @author zNick_
	 */
	public static EntityPlayer getPlayerFromUUID(UUID uuid)  {
	    List<EntityPlayerMP> allPlayers = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
	    for (EntityPlayerMP playerMP : allPlayers) if (playerMP.getUniqueID().equals(uuid)) return playerMP;
	    return null;
	}
}
