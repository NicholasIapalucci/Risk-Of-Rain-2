package znick_.riskofrain2.item.ror.list.green.atgmissile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHitItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class ATGMissileItem extends RiskOfRain2Item implements OnHitItem {

	public ATGMissileItem() {
		super("atg_missile");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.DAMAGE;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.GREEN;
	}

	@Override
	public String getDescription() {
		return "Chance to fire a missile.";
	}

	@Override
	public void procOnHit(LivingAttackEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount) {
		player.getWorld().spawnEntityInWorld(new ATGMissileEntity(player.getWorld(), player.x(), player.y() + 1, player.z(), itemCount));
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount) {
		return player.rollStat(0.1);
	}

}
