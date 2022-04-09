package znick_.riskofrain2.net;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.data.packets.FindItemPacketHandler;
import znick_.riskofrain2.api.mc.data.packets.PlayerHealPacketHandler;
import znick_.riskofrain2.api.mc.data.packets.SyncPlayerDataPacketHandler;
import znick_.riskofrain2.api.ror.artifact.list.command.gui.DropItemPacketHandler;
import znick_.riskofrain2.api.ror.buff.BuffPacketHandler;
import znick_.riskofrain2.api.ror.buff.stat.EntityStatUpdatePacketHandler;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.ArrowRainPacketHandler;
import znick_.riskofrain2.entity.util.EntityPacketHandler;

public class RiskOfRain2Packets {

	public static final SimpleNetworkWrapper NET = NetworkRegistry.INSTANCE.newSimpleChannel(RiskOfRain2Mod.MODID.toUpperCase());
	private static int nextPacketId = 0;
	
	public static void registerPackets() {
		registerMessage(PlayerHealPacketHandler.class, PlayerHealPacketHandler.PlayerHealPacket.class);
		registerMessage(ArrowRainPacketHandler.class, ArrowRainPacketHandler.ArrowRainPacket.class);
		registerMessage(SoundPacketHandler.class, SoundPacketHandler.SoundPacket.class);
		registerMessage(EntityStatUpdatePacketHandler.class, EntityStatUpdatePacketHandler.EntityStatUpdatePacket.class);
		registerMessage(BuffPacketHandler.class, BuffPacketHandler.BuffPacket.class);
		registerMessage(DropItemPacketHandler.class, DropItemPacketHandler.DropItemPacket.class);
		registerMessage(FindItemPacketHandler.class, FindItemPacketHandler.FindItemPacket.class);
		registerMessage(SyncPlayerDataPacketHandler.class, SyncPlayerDataPacketHandler.SyncPlayerDataPacket.class);
		registerMessage(EntityPacketHandler.class, EntityPacketHandler.EntityPacket.class);
	}
	
	private static void registerMessage(Class handler, Class packet) {
		NET.registerMessage(handler, packet, nextPacketId, Side.CLIENT);
		NET.registerMessage(handler, packet, nextPacketId, Side.SERVER);
		nextPacketId++;
	}
}
