package znick_.riskofrain2.item.ror.list.red;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.FriendlyExplosion;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHitItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class BrilliantBehemothItem extends RiskOfRain2Item implements OnHitItem {

	public BrilliantBehemothItem() {
		super("brilliant_behemoth");
	}
	
	@Override
	public void procOnHit(LivingAttackEvent event, EntityData player, EntityLivingBase enemy, int itemCount) {
		FriendlyExplosion.explodeMob(enemy, player.getEntity(), itemCount + 2);
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, EntityData player, EntityLivingBase enemy, int itemCount) {
		return true;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.DAMAGE;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.RED;
	}

	@Override
	public String getDescription() {
		return "All your attacks explode!";
	}
}
