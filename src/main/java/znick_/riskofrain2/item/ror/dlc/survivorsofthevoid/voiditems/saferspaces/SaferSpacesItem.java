package znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.voiditems.saferspaces;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.VoidItem;
import znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.voiditems.saferspaces.SaferSpacesBuffPacketHandler.SaferSpacesBuffPacket;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.net.RiskOfRain2Packets;

public class SaferSpacesItem extends VoidItem implements OnHurtItem, OnUpdateItem {

	public SaferSpacesItem() {
		super("safer_spaces");
	}

	@Override
	public RiskOfRain2Item[] getCorruptedItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.TOUGHER_TIMES};
	}

	@Override
	public String getItemDescription() {
		return "Block the next source of damage.";
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		return player.hasBuff(SaferSpacesBuff.class);
	}
	
	@Override
	public void procOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		event.setCanceled(true);
		player.removeBuff(SaferSpacesBuff.class);
		IMessage packet = new SaferSpacesBuffPacket();
		RiskOfRain2Packets.NET.sendTo(packet, (EntityPlayerMP) player.getPlayer());
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return !(player.hasBuff(SaferSpacesBuff.class) || player.hasBuff(SaferSpacesCooldownBuff.class)); 
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		player.addBuff(new SaferSpacesBuff(itemCount));
	}
}
