package znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.voiditems.weepingfungus;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Files;

public class WeepingFungusBuff extends Buff {

	public WeepingFungusBuff(int itemCount) {
		super(itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return new ResourceLocation(RiskOfRain2Files.BUFFS + "weeping_fungus.png");
	}

	@Override
	public void applyEffect(AbstractEntityData player) {
		if(!player.getWorld().isRemote) {
			player.heal((float) ((player.getMaxHealth() * 0.02 * this.getItemCount()) / TickHandler.fromSeconds(1))); 
		}
	}

	@Override
	public void removeEffect(AbstractEntityData player) {
		
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.WEEPING_FUNGUS};
	}

	@Override
	public boolean shouldRepeat() {
		return true;
	}
	
}
