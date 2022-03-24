package znick_.riskofrain2.item.ror.list.green.huntersharpoon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnKillItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class HuntersHarpoonItem extends RiskOfRain2Item implements OnKillItem {

	public HuntersHarpoonItem() {
		super("hunters_harpoon");
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
		return "Killing an enemy gives you a burst of movement speed.";
	}

	@Override
	public void procOnKill(LivingDeathEvent event, EntityData player, EntityLivingBase enemy, int itemCount) {
		for (int i = 1; i <= 5; i++) {
			player.addBuff(new HuntersHarpoonBuff(itemCount).setDuration(i * TickHandler.fromSeconds(0.2)));
		}
	}

}
