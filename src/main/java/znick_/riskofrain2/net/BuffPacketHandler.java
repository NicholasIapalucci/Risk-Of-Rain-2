package znick_.riskofrain2.net;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.net.BuffPacketHandler.BuffPacket;

public class BuffPacketHandler implements IMessageHandler<BuffPacket, IMessage> {

	@Override
	public IMessage onMessage(BuffPacket message, MessageContext ctx) {
		PlayerData.get(ctx.side.isServer()? ctx.getServerHandler().playerEntity : Minecraft.getMinecraft().thePlayer).addBuff(message.buff, false);
		return null;
	}
	
	public static class BuffPacket implements IMessage {
		
		private Buff buff;
		
		public BuffPacket(Buff buff) {
			this.buff = buff;
		}
		
		public BuffPacket() {}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			try {this.buff = Buff.fromID(buf.readInt()).getDeclaredConstructor(int.class).newInstance(buf.readInt());}
			catch(Exception e) {throw new RuntimeException(e);}
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(this.buff.getID());
			buf.writeInt(this.buff.getItemCount());
		}
		
	}
}
