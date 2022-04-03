package znick_.riskofrain2.api.ror.survivor.ability.phase;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.net.RiskOfRain2Packets;

public interface AbilityPhase {
	
	public abstract void activatePhase(EntityPlayer player);

}
