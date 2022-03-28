package znick_.riskofrain2.item.ror.list.white.rollofpennies;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class RollOfPenniesItem extends RiskOfRain2Item implements OnHurtItem {

	public RollOfPenniesItem() {
		super("roll_of_pennies");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Gain gold on taking damage.";
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, AbstractEntityData entity, int itemCount) {
		return entity.isPlayer();
	}
	
	@Override
	public void procOnHurt(LivingHurtEvent event, AbstractEntityData entity, int itemCount) {
		((PlayerData) entity).addMoney(3 * itemCount);
	}

}
