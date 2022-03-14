package znick_.riskofrain2.item.ror.list.red.defensivemicrobots;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class DefensiveMicrobotsItem extends RiskOfRain2Item implements OnUpdateItem {

	public DefensiveMicrobotsItem() {
		super("defensive_microbots");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.RED;
	}

	@Override
	public String getDescription() {
		return "Shoot down nearby projectiles. Recharge rate scales with attack speed.";
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return !player.hasBuff(DefensiveMicrobotsCooldownBuff.class);
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		AxisAlignedBB boundingBox = player.radialBox(2);
		List<Entity> projectiles = player.getWorld().getEntitiesWithinAABB(IProjectile.class, boundingBox);
		if (projectiles.isEmpty()) return;
		
		// Find which projectile is closest
		Entity closestProjectile = projectiles.get(0);
		for (Entity entity : projectiles) {
			if (entity.getDistanceToEntity(player.getPlayer()) < closestProjectile.getDistanceToEntity(player.getPlayer())) {
				closestProjectile = entity;
			}
		}
		
		// Kill the closest projectile
		closestProjectile.setDead();
		player.addBuff(new DefensiveMicrobotsCooldownBuff(itemCount));
	}

}
