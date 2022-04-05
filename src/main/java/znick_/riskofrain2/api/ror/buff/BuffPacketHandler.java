package znick_.riskofrain2.api.ror.buff;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.BuffPacketHandler.BuffPacket;

public class BuffPacketHandler implements IMessageHandler<BuffPacket, IMessage> {

	@Override
	public IMessage onMessage(BuffPacket message, MessageContext ctx) {
		PlayerData player = PlayerData.get(ctx.side.isServer()? ctx.getServerHandler().playerEntity : Minecraft.getMinecraft().thePlayer);
		if (!message.remove) player.addBuff(message.buff, false);
		else player.removeBuff(message.buff.getClass(), false);
		return null;
	}
	
	public static class BuffPacket implements IMessage {
		
		private Buff buff;
		private int duration;
		private boolean remove;
		
		public BuffPacket(Buff buff) {
			this.buff = buff;
			this.remove = false;
		}
		
		public BuffPacket(Class<? extends Buff> buff) {
			try {this.buff = buff.getDeclaredConstructor(int.class).newInstance(0);}
			catch(Exception e) {e.printStackTrace();}
			this.remove = true;
		}
		
		public BuffPacket() {}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			try {
				this.buff = Buff.fromID(buf.readInt()).getDeclaredConstructor(int.class).newInstance(buf.readInt());
				if (this.buff instanceof DurationBuff) {
					DurationBuff db = (DurationBuff) this.buff;
					db.setDuration(buf.readInt());
					db.setStartTick(buf.readInt());
				}
				this.remove = buf.readBoolean();
			}
			catch(Exception e) {e.printStackTrace();}
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(this.buff.getID());
			buf.writeInt(this.buff.getItemCount());
			if (this.buff instanceof DurationBuff) {
				DurationBuff db = (DurationBuff) this.buff;
				buf.writeInt(db.getDuration());
				buf.writeInt(db.getStartTick());
			}
			buf.writeBoolean(this.remove);
		}
		
	}
}
