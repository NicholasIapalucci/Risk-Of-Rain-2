package znick_.riskofrain2.api.ror.items.green;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.item.OnHitItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class Chronobauble extends RiskOfRain2Item implements OnHitItem {

	public Chronobauble() {
		super("chronobauble");
	}

	@Override
	public void procOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		enemy.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, itemCount * 40, 1));
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		return true;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.GREEN;
	}

	@Override
	public String getDescription() {
		return "Slow enemies on hit.";
	}

}
