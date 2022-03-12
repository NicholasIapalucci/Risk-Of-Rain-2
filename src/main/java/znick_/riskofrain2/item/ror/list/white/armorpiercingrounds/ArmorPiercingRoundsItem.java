package znick_.riskofrain2.item.ror.list.white.armorpiercingrounds;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHitItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.util.helper.EntityHelper;

public class ArmorPiercingRoundsItem extends RiskOfRain2Item implements OnHitItem {

	public ArmorPiercingRoundsItem() {
		super("armor_piercing_rounds");
	}
	
	@Override
	public void procOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		player.addBuff(new ArmorPiercingRoundsBuff(itemCount));
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
