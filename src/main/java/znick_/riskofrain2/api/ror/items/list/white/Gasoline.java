package znick_.riskofrain2.api.ror.items.list.white;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.proc.type.OnKillItem;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;

public class Gasoline extends RiskOfRain2Item implements OnKillItem {

	public Gasoline() {
		super("gasoline");
	}
	
	@Override
	public void procOnKill(LivingDeathEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		AxisAlignedBB boundingBox = player.radialBox(itemCount + 5);
		List<EntityLivingBase> mobs = player.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, boundingBox);
		for (EntityLivingBase mob : mobs) mob.setFire(itemCount + 2);
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
		return "Killing enemies sets nearby enemies on fire.";
	}

}
