package znick_.riskofrain2.item.ror.list.white.cautiousslug;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class CautiousSlugItem extends RiskOfRain2Item implements OnUpdateItem, OnHurtItem {
	
	public CautiousSlugItem() {
		super("cautious_slug");
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return !(player.hasBuff(CautiousSlugCooldownBuff.class) || player.getWorld().isRemote);
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		if (!player.hasBuff(CautiousSlugBuff.class)) player.playSound("ror2:cautious_slug_start");
		player.addBuff(new CautiousSlugBuff(itemCount));
	}
	
	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		return true;
	}
	
	@Override
	public void procOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		if (player.hasBuff(CautiousSlugBuff.class)) {
			player.removeBuff(CautiousSlugBuff.class);
			player.addBuff(new CautiousSlugCooldownBuff(itemCount));
			player.playSound("ror2:cautious_slug_stop");
		}
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
		return "Rapidly heal outside of danger.";
	}
	
}
