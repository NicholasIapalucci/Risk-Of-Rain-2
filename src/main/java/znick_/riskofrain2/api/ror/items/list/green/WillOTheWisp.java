package znick_.riskofrain2.api.ror.items.list.green;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import znick_.riskofrain2.api.mc.FriendlyExplosion;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.proc.type.OnKillItem;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;

public class WillOTheWisp extends RiskOfRain2Item implements OnKillItem {

	public WillOTheWisp() {
		super("will_o_the_wisp");
	}
	
	@Override
	public void procOnKill(LivingDeathEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		FriendlyExplosion.explodeMob(enemy, player.getPlayer(), itemCount);
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
		return "Detonate enemies on kill.";
	}
}
