package znick_.riskofrain2.api.ror.survivor.ability.phase;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.net.RiskOfRain2Packets;

public interface AbilityPhase {
	public abstract void activatePhase(EntityPlayer player);
	
	public default Side sideToRunOn() {
		return Side.CLIENT;
	}
	
	public default IMessage createPacket() {
		return null;
	}
	
	/**
	 * Activates the ability. Calls the {@link #activatePhase(EntityPlayer)} method on the server or the
	 * client, depending on the result of {@link #sideToRunOn()}.
	 */
	public default void activate() {
		if (this.sideToRunOn() == Side.CLIENT) this.activatePhase(Minecraft.getMinecraft().thePlayer);
		else {
			IMessage packet = this.createPacket();
			RiskOfRain2Packets.NET.sendToServer(packet);
		}
	}
	
}
