package znick_.riskofrain2.item.ror.list.red.ceremonialdagger;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnKillItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class CeremonialDaggerItem extends RiskOfRain2Item implements OnKillItem {

	public CeremonialDaggerItem() {
		super("ceremonial_dagger");
	}

	@Override
	public void procOnKill(LivingDeathEvent event, AbstractEntityData player, EntityLivingBase enemy, int itemCount) {
		for (int i = 0; i < 3; i++) {
			player.getWorld().spawnEntityInWorld(new CeremonialDaggerEntity(player.getWorld(), enemy.posX + Math.random(), enemy.posY + Math.random(), enemy.posZ + Math.random(), itemCount));
		}
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.DAMAGE;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.RED;
	}

	@Override
	public String getDescription() {
		return "Killing an enemy releases homing daggers.";
	}

}
