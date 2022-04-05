package znick_.riskofrain2.api.mc.data.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import znick_.riskofrain2.api.mc.data.packets.PlayerHealPacketHandler.PlayerHealPacket;

public class PlayerHealPacketHandler implements IMessageHandler<PlayerHealPacket, IMessage> {

	@Override
	public IMessage onMessage(PlayerHealPacket message, MessageContext ctx) {
		if (ctx.side.isServer()) ctx.getServerHandler().playerEntity.heal(message.health);
		return null;
	}
	
	public static class PlayerHealPacket implements IMessage {
		
		private float health;
		
		public PlayerHealPacket(float health) {
			this.health = health;
		}
		
		public PlayerHealPacket() {}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			this.health = buf.readFloat();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeFloat(this.health);
		}
	}
}
