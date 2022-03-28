package znick_.riskofrain2.net;

import java.io.IOException;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.net.SyncPlayerDataPacketHandler.SyncPlayerDataPacket;

public class SyncPlayerDataPacketHandler implements IMessageHandler<SyncPlayerDataPacket, IMessage> {

	@Override
	public IMessage onMessage(SyncPlayerDataPacket message, MessageContext ctx) {
		if (ctx.side.isClient()) PlayerData.get(Minecraft.getMinecraft().thePlayer).loadNBTData(message.nbt);
		return null;
	}
	
	public static class SyncPlayerDataPacket implements IMessage {

		private NBTTagCompound nbt;
		
		public SyncPlayerDataPacket() {}
		
		public SyncPlayerDataPacket(PlayerData player) {
			this.nbt = new NBTTagCompound();
			player.saveNBTData(this.nbt);
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			try {this.nbt = new PacketBuffer(buf).readNBTTagCompoundFromBuffer();}
			catch(IOException e) {throw new RuntimeException(e);}
		}

		@Override
		public void toBytes(ByteBuf buf) {
			try {new PacketBuffer(buf).writeNBTTagCompoundToBuffer(this.nbt);}
			catch(IOException e) {throw new RuntimeException(e);}
		}
		
	}
}
