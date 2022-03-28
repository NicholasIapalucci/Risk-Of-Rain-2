package znick_.riskofrain2.item.ror.list.white.personalshieldgenerator;

import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class PersonalShieldGeneratorItem extends RiskOfRain2Item implements OnUpdateItem {
	
	public PersonalShieldGeneratorItem() {
		super("personal_shield");
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount) {
		player.addBuff(new PersonalShieldBuff(itemCount));
	}
	
	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount) {
		return true;
	}
	
	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Gain a recharging sheild.";
	}
}
