package znick_.riskofrain2.api.ror.items.list.green;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.proc.type.OnHitItem;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;

public class LeechingSeed extends RiskOfRain2Item implements OnHitItem {

	public LeechingSeed() {
		super("leeching_seed");
	}

	@Override
	public void procOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		if (!player.getWorld().isRemote) player.heal(itemCount);
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
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
		return "Dealing damage heals you";
	}

}
