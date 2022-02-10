package znick_.riskofrain2.api.ror.items.list.white;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.PlayerStat;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.proc.type.OnHitItem;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;
import znick_.riskofrain2.util.helper.EntityHelper;

public class ArmorPiercingRoundsItem extends RiskOfRain2Item implements OnHitItem {

	public ArmorPiercingRoundsItem() {
		super("armor_piercing_rounds");
	}
	
	@Override
	public void procOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		player.multiplyStat(PlayerStat.DAMAGE_MULTIPLIER, 1 + 0.2 * itemCount);
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		return EntityHelper.isBoss(enemy);
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.DAMAGE;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Deal extra damage to bosses.";
	}
}
