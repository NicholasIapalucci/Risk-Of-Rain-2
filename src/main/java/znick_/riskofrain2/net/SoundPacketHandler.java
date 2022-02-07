package znick_.riskofrain2.net;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import znick_.riskofrain2.net.SoundPacketHandler.SoundPacket;

public class SoundPacketHandler implements IMessageHandler<SoundPacket, IMessage> {

	@Override
	public IMessage onMessage(SoundPacket message, MessageContext ctx) {
		if (ctx.side.isClient()) Minecraft.getMinecraft().thePlayer.playSound(message.sound, 1, 1);
		return null;
	}
	
	public static class SoundPacket implements IMessage {
		
		private String sound;
		
		public SoundPacket(String sound) {
			this.sound = sound;
		}
		
		public SoundPacket() {}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			this.sound = ByteBufUtils.readUTF8String(buf);
		}

		@Override
		public void toBytes(ByteBuf buf) {
			ByteBufUtils.writeUTF8String(buf, this.sound);
		}
	}
}
