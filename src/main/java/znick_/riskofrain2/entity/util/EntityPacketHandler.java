package znick_.riskofrain2.entity.util;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import znick_.riskofrain2.entity.util.EntityPacketHandler.EntityPacket;

public class EntityPacketHandler implements IMessageHandler<EntityPacket, IMessage> {

	@Override
	public IMessage onMessage(EntityPacket message, MessageContext ctx) {
		
		if (ctx.side.isServer()) {
			Entity entity = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityID);
			message.onServerMessage(entity);
		}
		
		else {
			Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(message.entityID);
			message.onClientMessage(entity);
		}
		
		return null;
	}
	
	public static class EntityPacket implements IMessage {
		
		private int entityID;
		
		public EntityPacket() {}
		
		public EntityPacket(Entity entity) {
			this.entityID = entity.getEntityId();
		}
		
		public void onServerMessage(Entity entity) {}
		public void onClientMessage(Entity entity) {}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.entityID = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(this.entityID);
		}
	}
}
