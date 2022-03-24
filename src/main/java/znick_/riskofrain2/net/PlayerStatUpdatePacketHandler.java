package znick_.riskofrain2.net;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.net.PlayerStatUpdatePacketHandler.PlayerStatUpdatePacket;

public class PlayerStatUpdatePacketHandler implements IMessageHandler<PlayerStatUpdatePacket, IMessage> {

	@Override
	public IMessage onMessage(PlayerStatUpdatePacket message, MessageContext ctx) {
		PlayerData.get(ctx.side.isServer()? ctx.getServerHandler().playerEntity : Minecraft.getMinecraft().thePlayer).setStat(message.stat, message.amount, false);
		return null;
	}
	
	public static class PlayerStatUpdatePacket implements IMessage {
		
		private EntityStat stat;
		private double amount;
		
		public PlayerStatUpdatePacket(EntityStat stat, double amount) {
			this.stat = stat;
			this.amount = amount;
		}
		
		public PlayerStatUpdatePacket() {}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.stat = EntityStat.values()[buf.readInt()];
			this.amount = buf.readDouble();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(this.stat.ordinal());
			buf.writeDouble(this.amount);
		}
	}
}
