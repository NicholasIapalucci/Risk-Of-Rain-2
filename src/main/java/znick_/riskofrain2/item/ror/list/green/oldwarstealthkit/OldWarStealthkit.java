package znick_.riskofrain2.item.ror.list.green.oldwarstealthkit;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class OldWarStealthkit extends RiskOfRain2Item implements OnHurtItem  {
	
	public OldWarStealthkit() {
		super("old_war_stealthkit");
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
		return "Turn invisible at low health";
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, AbstractEntityData player, int itemCount) {
		return !player.hasBuff(OldWarStealthkitCooldownBuff.class) && player.getHealth() - event.ammount < player.getMaxHealth() * 0.25;
	}
	
	@Override
	public void procOnHurt(LivingHurtEvent event, AbstractEntityData player, int itemCount) {
		player.addBuff(new OldWarStealthkitInvisibilityBuff(itemCount));
		player.addBuff(new OldWarStealthkitSpeedBuff(itemCount));
		player.addBuff(new OldWarStealthkitCooldownBuff(itemCount));
	}
}
