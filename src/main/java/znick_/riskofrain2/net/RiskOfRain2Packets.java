package znick_.riskofrain2.net;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.ArrowRainPacket;

public class RiskOfRain2Packets {

	public static final SimpleNetworkWrapper NET = NetworkRegistry.INSTANCE.newSimpleChannel(RiskOfRain2.MODID.toUpperCase());
	private static int nextPacketId = 0;
	
	public static void registerPackets() {
		registerMessage(PlayerHealPacketHandler.class, PlayerHealPacketHandler.PlayerHealPacket.class);
		registerMessage(AbilityPacketHandler.class, AbilityPacketHandler.AbilityPacket.class);
		registerMessage(ArrowRainPacket.class, ArrowRainPacket.ArrowRainMessage.class);
	}
	
	private static void registerMessage(Class handler, Class packet) {
		NET.registerMessage(handler, packet, nextPacketId, Side.CLIENT);
		NET.registerMessage(handler, packet, nextPacketId, Side.SERVER);
		nextPacketId++;
	}
}
