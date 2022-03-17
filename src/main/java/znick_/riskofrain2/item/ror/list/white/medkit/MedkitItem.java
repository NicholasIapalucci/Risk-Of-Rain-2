package znick_.riskofrain2.item.ror.list.white.medkit;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class MedkitItem extends RiskOfRain2Item implements OnHurtItem {
	
	public MedkitItem() {
		super("medkit");
	}

	@Override
	public void procOnHurt(LivingHurtEvent event, AbstractEntityData player, int itemCount) {
		player.addBuff(new MedkitBuff(itemCount));
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, AbstractEntityData player, int itemCount) {
		return true;
	}
	
	@Override
	public ItemCategory getCategory() {
		return ItemCategory.HEALING;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Receive a delayed heal after taking damage.";
	}
}