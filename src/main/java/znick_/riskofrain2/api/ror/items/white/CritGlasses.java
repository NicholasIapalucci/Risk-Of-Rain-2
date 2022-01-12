package znick_.riskofrain2.api.ror.items.white;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.proc.item.OnHitItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.util.helper.InventoryHelper;

public class CritGlasses extends RiskOfRain2Item implements OnHitItem {

	public CritGlasses() {
		super("crit_glasses");
	}

	@Override
	public void procOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		player.playSound("ror2:crit_glasses", 1, 1);
		int harvestersScytheAmount = player.itemCount(RiskOfRain2Items.HARVESTERS_SCYTHE);
		if (harvestersScytheAmount > 0) {
			((OnHitItem) RiskOfRain2Items.HARVESTERS_SCYTHE).procOnHit(event, player, enemy, harvestersScytheAmount);
		}
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		return false;
	}
	
	@Override
	public String getProperName() {
		return "Lens-Maker's Glasses";
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
		return "Chance to \'Critically Strike\' dealing double damage.";
	}

}
