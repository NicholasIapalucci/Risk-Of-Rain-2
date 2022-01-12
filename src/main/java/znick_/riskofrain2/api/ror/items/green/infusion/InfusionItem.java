package znick_.riskofrain2.api.ror.items.green.infusion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.item.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class InfusionItem extends RiskOfRain2Item implements OnUpdateItem {

	public InfusionItem() {
		super("infusion");
	}

	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		player.addBuff(new InfusionBuff(itemCount));
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return true;
	}
	
	@Override
	public ItemCategory getCategory() {
		return ItemCategory.HEALING;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.GREEN;
	}

	@Override
	public String getDescription() {
		return "Permanently increases health.";
	}
}