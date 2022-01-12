package znick_.riskofrain2.api.ror.items.white.medkit;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.item.OnHurtItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class Medkit extends RiskOfRain2Item implements OnHurtItem {
	
	public Medkit() {
		super("medkit");
	}

	@Override
	public void procOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		player.addBuff(new MedkitBuff(itemCount));
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
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