package znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import znick_.riskofrain2.api.mc.Position;
import znick_.riskofrain2.api.ror.survivor.SurvivorEventHandler;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.ArrowRainAbility.ArrowRainPhase3;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain.ArrowRainPacket.ArrowRainMessage;

public class ArrowRainPacket implements IMessageHandler<ArrowRainMessage, IMessage> {

	@Override
	public ArrowRainMessage onMessage(ArrowRainMessage message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			ArrowRainPhase3 phase = new ArrowRainAbility().new ArrowRainPhase3();
			phase.arrowRainBlock = new Position(message.x, message.y, message.z);
			SurvivorEventHandler.activateRepeatingAbility(ctx.getServerHandler().playerEntity, phase);
			ctx.getServerHandler().playerEntity.worldObj.playSound(message.x, message.y, message.z, "ror2:huntress_arrowrain_start", 1, 1, true);
		}
		return null;
	}
	
	public static class ArrowRainMessage implements IMessage {

		private int x;
		private int y;
		private int z;
		
		public ArrowRainMessage() {}
		
		public ArrowRainMessage(int x, int y, int z) {
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
