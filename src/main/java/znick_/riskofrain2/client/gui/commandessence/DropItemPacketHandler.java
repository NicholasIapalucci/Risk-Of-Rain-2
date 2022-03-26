package znick_.riskofrain2.client.gui.commandessence;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import znick_.riskofrain2.client.gui.commandessence.DropItemPacketHandler.DropItemPacket;

public class DropItemPacketHandler implements IMessageHandler<DropItemPacket, IMessage> {

	@Override
	public IMessage onMessage(DropItemPacket message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(new EntityItem(ctx.getServerHandler().playerEntity.worldObj, message.x, message.y, message.z, message.itemStack));
		}
		
		return null;
	}
	
	public static class DropItemPacket implements IMessage {

		private ItemStack itemStack;
		private double x;
		private double y;
		private double z;
		
		public DropItemPacket() {}
		
		public DropItemPacket(ItemStack stack, double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.itemStack = stack;
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			this.x = buf.readDouble();
			this.y = buf.readDouble();
			this.z = buf.readDouble();
			this.itemStack = ByteBufUtils.readItemStack(buf);
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeDouble(this.x);
			buf.writeDouble(this.y);
			buf.writeDouble(this.z);
			ByteBufUtils.writeItemStack(buf, this.itemStack);
		}
		
	}
}
