package znick_.riskofrain2.item.ror.list.red.defensivemicrobots;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.util.helper.MapHelper;

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
		
		// Get any projectiles near the player
		AxisAlignedBB boundingBox = player.radialBox(2);
		List<Entity> projectiles = player.getWorld().getEntitiesWithinAABB(IProjectile.class, boundingBox);
		if (projectiles.isEmpty()) return;
		
		// Sort projectiles by how close they are to the player
		Map<Entity, Double> distances = new HashMap<>();
		for (Entity entity : projectiles) distances.put(entity, (double) entity.getDistanceToEntity(player.getPlayer()));
		Map<Entity, Double> sortedDistances = MapHelper.sortByValue(distances);
		
		// Kill the closest projectile(s)
		Iterator<Entity> iterator = sortedDistances.keySet().iterator();
		for (int i = 0; iterator.hasNext() && i < itemCount; i++) iterator.next().setDead();
		
		// Put the item on cooldown
		player.addBuff(new DefensiveMicrobotsCooldownBuff(itemCount));
	}

}
