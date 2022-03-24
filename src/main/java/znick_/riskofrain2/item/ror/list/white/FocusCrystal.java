package znick_.riskofrain2.item.ror.list.white;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.ror.buff.EntityStat;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHitItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.util.helper.MathHelper;

public class FocusCrystal extends RiskOfRain2Item implements OnHitItem {

	public FocusCrystal() {
		super("focus_crystal");
	}

	@Override
	public void procOnHit(LivingAttackEvent event, EntityData player, EntityLivingBase enemy, int itemCount) {
		player.addToStat(EntityStat.DAMAGE_MULTIPLIER, 1 + 0.2 * itemCount);
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, EntityData player, EntityLivingBase enemy, int itemCount) {
		return player.distanceFrom(enemy) < 4;
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
		return "Deal bonus damage to nearby enemies.";
	}
}
