package znick_.riskofrain2.item.ror.list.white.topazbrooch;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.proc.type.OnKillItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class TopazBroochItem extends RiskOfRain2Item implements OnKillItem {

	public TopazBroochItem() {
		super("topaz_brooch");
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
		return "Gain a temporary barrier on kill.";
	}

	@Override
	public void procOnKill(LivingDeathEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount) {
		player.addBarrier(itemCount * 2);
	}

}
