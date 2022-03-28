package znick_.riskofrain2.item.ror.list.red;

import net.minecraftforge.event.entity.living.LivingHealEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHealItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

/**
 * Class used to create the {@link znick_.riskofrain2.item.RiskOfRain2Items#REJUVINATION_RACK REJUVINATION_RACK} item.
 * @author zNick_
 */
public class RejuvinationRack extends RiskOfRain2Item implements OnHealItem {

	/**
	 * Creates a new {@codew RejuvinationRoack} item.
	 */
	public RejuvinationRack() {
		super("rejuvination_rack");
	}
	
	@Override
	public void procOnHeal(LivingHealEvent event, AbstractEntityData player, int itemCount) {
		if (!player.getEntity().worldObj.isRemote) event.amount *= (itemCount + 1);
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
