package znick_.riskofrain2.item.ror.list.white.warbanner;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

/**
 * The buff the player receives when in radius of a warbanner. The buff normally would not be a {@code DurationBuff},
 * but the warbanner tile entity has no efficient way of removing the buff when a player leaves the radius, so the 
 * buff must expire naturally by being a {@code DurationBuff}.
 * 
 * @author zNick_
 */
public class WarbannerBuff extends DurationBuff {
	
	/**
	 * Creats a new {@code WarbannerBuff} for the player.
	 * 
	 * @param itemCount The level of the war banner.
	 */
	public WarbannerBuff(int itemCount) {
		super(itemCount, (int) TickHandler.fromSeconds(3));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "warbanner.png");
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		player.addToStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, 0.3);
	}
	
	@Override
	public void removeEffect(AbstractEntityData player) {
		player.addToStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER, -0.3);
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.WAR_BANNER};
	}

}
