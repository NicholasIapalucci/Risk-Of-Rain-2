package znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import znick_.riskofrain2.api.mc.Position;
import znick_.riskofrain2.api.ror.survivor.SurvivorEventHandler;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.ArrowRainAbility.ArrowRainPhase3;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.ArrowRainPacketHandler.ArrowRainPacket;

public class ArrowRainPacketHandler implements IMessageHandler<ArrowRainPacket, IMessage> {

	@Override
	public ArrowRainPacket onMessage(ArrowRainPacket message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			ArrowRainPhase3 phase = new ArrowRainAbility().new ArrowRainPhase3();
			phase.arrowRainBlock = new Position(message.x, message.y, message.z);
			SurvivorEventHandler.activateRepeatingAbility(ctx.getServerHandler().playerEntity, phase);		
		}
		return null;
	}
	
	public static class ArrowRainPacket implements IMessage {

		private int x;
		private int y;
		private int z;
		
		public ArrowRainPacket() {}
		
		public ArrowRainPacket(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		@Override
		public void fromBytes(ByteBuf buf) {
			this.x = buf.readInt();
			this.y = buf.readInt();
			this.z = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(this.x);
			buf.writeInt(this.y);
			buf.writeInt(this.z);
		}
		
	}
}
