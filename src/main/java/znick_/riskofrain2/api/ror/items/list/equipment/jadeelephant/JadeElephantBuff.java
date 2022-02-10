package znick_.riskofrain2.api.ror.items.list.equipment.jadeelephant;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.DurationBuff;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class JadeElephantBuff extends DurationBuff {

	private static final ResourceLocation TEXTURE = new ResourceLocation(RiskOfRain2Files.BUFFS + "jade_elephant.png");
	
	public JadeElephantBuff() {
		super((RiskOfRain2Item) RiskOfRain2Items.JADE_ELEPHANT, 0, TickHandler.fromSeconds(5));
	}

	@Override
	public ResourceLocation getIconTexture() {
		return TEXTURE;
	}

	@Override
	public void applyEffect(PlayerData player) {
		player.addToStat(PlayerStat.ARMOR, 500);
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.addToStat(PlayerStat.ARMOR, -500);
	}

	@Override
	public boolean isDebuff() {
		return false;
	}

}
