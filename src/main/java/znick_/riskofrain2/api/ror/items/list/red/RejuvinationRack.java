package znick_.riskofrain2.api.ror.items.list.red;

import net.minecraftforge.event.entity.living.LivingHealEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.proc.type.OnHealItem;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;

/**
 * Class used to create the {@link znick_.riskofrain2.item.RiskOfRain2Items#REJUVINATION_RACK REJUVINATION_RACK} item.
 * @author Nicholas Iapalucci
 */
public class RejuvinationRack extends RiskOfRain2Item implements OnHealItem {

	/**
	 * Creates a new {@codew RejuvinationRoack} item.
	 */
	public RejuvinationRack() {
		super("rejuvination_rack");
	}
	
	@Override
	public void procOnHeal(LivingHealEvent event, PlayerData player, int itemCount) {
		if (!player.getPlayer().worldObj.isRemote) event.amount *= (itemCount + 1);
	}
	
	@Override
	public ItemCategory getCategory() {
		return ItemCategory.HEALING;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.RED;
	}

	@Override
	public String getDescription() {
		return "Double the strength of healing";
	}
}
