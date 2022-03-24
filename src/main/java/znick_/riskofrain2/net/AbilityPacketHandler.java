package znick_.riskofrain2.net;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Loadout;
import znick_.riskofrain2.client.keybind.RiskOfRain2KeyBinds;
import znick_.riskofrain2.net.AbilityPacketHandler.AbilityPacket;

public class AbilityPacketHandler implements IMessageHandler<AbilityPacket, IMessage> {

	@Override
	public IMessage onMessage(AbilityPacket message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			
		}
		
		return null;
	}
	
	public static class AbilityPacket implements IMessage {

		public AbilityPacket() {}
		
		@Override
		public void fromBytes(ByteBuf buf) {}

		@Override
		public void toBytes(ByteBuf buf) {}
		
	}
}
