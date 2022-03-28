package znick_.riskofrain2.net;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.net.FindItemPacketHandler.FindItemPacket;

public class FindItemPacketHandler implements IMessageHandler<FindItemPacket, IMessage> {

	@Override
	public IMessage onMessage(FindItemPacket message, MessageContext ctx) {
		PlayerData.get(ctx.side.isServer()? ctx.getServerHandler().playerEntity : Minecraft.getMinecraft().thePlayer).find((RiskOfRain2Item) message.item.getItem(), false);
		return null;
	}
	
	public static class FindItemPacket implements IMessage {

		private ItemStack item;
		
		public FindItemPacket() {}
		
		public FindItemPacket(RiskOfRain2Item item) {
			this.item = new ItemStack(item);
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			this.item = ByteBufUtils.readItemStack(buf);
		}

		@Override
		public void toBytes(ByteBuf buf) {
			ByteBufUtils.writeItemStack(buf, this.item);
		}
		
	}
}
