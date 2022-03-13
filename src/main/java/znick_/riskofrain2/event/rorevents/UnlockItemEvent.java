package znick_.riskofrain2.event.rorevents;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class UnlockItemEvent extends PlayerEvent {

	private final RiskOfRain2Item item;
	
	public UnlockItemEvent(EntityPlayer player, RiskOfRain2Item item) {
		super(player);
		this.item = item;
	}
	
	public RiskOfRain2Item getItem() {
		return this.item;
	}

}
