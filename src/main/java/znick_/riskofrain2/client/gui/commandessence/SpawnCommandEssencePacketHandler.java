package znick_.riskofrain2.client.gui.commandessence;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import znick_.riskofrain2.client.gui.commandessence.SpawnCommandEssencePacketHandler.SpawnCommandEssencePacket;
import znick_.riskofrain2.entity.inanimate.CommandEssenceEntity;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class SpawnCommandEssencePacketHandler implements IMessageHandler<SpawnCommandEssencePacket, IMessage> {

	@Override
	public IMessage onMessage(SpawnCommandEssencePacket message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(new CommandEssenceEntity(ctx.getServerHandler().playerEntity.worldObj, message.x, message.y, message.z, message.rarity));
		}
		
		return null;
	}
	
	public static class SpawnCommandEssencePacket implements IMessage {

		private double x;
		private double y;
		private double z;
		private ItemRarity rarity;
		
		public SpawnCommandEssencePacket() {}
		
		public SpawnCommandEssencePacket(ItemRarity rarity, double x, double y, double z) {
			this.rarity = rarity;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			this.rarity = ItemRarity.values()[buf.readInt()];
			this.x = buf.readDouble();
			this.y = buf.readDouble();
			this.z = buf.readDouble();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(this.rarity.ordinal());
			buf.writeDouble(this.x);
			buf.writeDouble(this.y);
			buf.writeDouble(this.z);
		}
		
	}
}
