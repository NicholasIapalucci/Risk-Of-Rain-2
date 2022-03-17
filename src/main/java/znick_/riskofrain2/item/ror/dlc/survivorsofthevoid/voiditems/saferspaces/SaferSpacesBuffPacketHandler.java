package znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.voiditems.saferspaces;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.voiditems.saferspaces.SaferSpacesBuffPacketHandler.SaferSpacesBuffPacket;

public class SaferSpacesBuffPacketHandler implements IMessageHandler<SaferSpacesBuffPacket, IMessage> {

	@Override
	public IMessage onMessage(SaferSpacesBuffPacket message, MessageContext ctx) {
		AbstractEntityData.get(Minecraft.getMinecraft().thePlayer).removeBuff(SaferSpacesBuff.class);
		return null;
	}
	
	public static class SaferSpacesBuffPacket implements IMessage {

		public SaferSpacesBuffPacket() {}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			
		}

		@Override
		public void toBytes(ByteBuf buf) {
			
		}
		
	}
}
