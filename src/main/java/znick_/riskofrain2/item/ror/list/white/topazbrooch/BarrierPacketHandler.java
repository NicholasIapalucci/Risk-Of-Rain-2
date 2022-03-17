package znick_.riskofrain2.item.ror.list.white.topazbrooch;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.list.white.topazbrooch.BarrierPacketHandler.BarrierPacket;

public class BarrierPacketHandler implements IMessageHandler<BarrierPacket, IMessage> {

	@Override
	public IMessage onMessage(BarrierPacket message, MessageContext ctx) {
		AbstractEntityData.get(ctx.side.isServer()? ctx.getServerHandler().playerEntity : Minecraft.getMinecraft().thePlayer).setBarrier(message.barrier, false);
		return null;
	}
	
	public static class BarrierPacket implements IMessage {

		private int barrier;
		
		public BarrierPacket() {}
		
		public BarrierPacket(int barrier) {
			this.barrier = barrier;
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			this.barrier = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(this.barrier);
		}
	}
}
