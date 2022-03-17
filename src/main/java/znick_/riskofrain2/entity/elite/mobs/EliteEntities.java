package znick_.riskofrain2.entity.elite.mobs;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import znick_.riskofrain2.entity.elite.EliteType;

public class EliteEntities {
	
	private static final Map<Class<? extends Entity>, Map<EliteType, Class<? extends Entity>>> ELITE_MAP = new HashMap<>();
	
	public static void registerEliteVariant(Class<? extends Entity> entityClass, Class<? extends Entity> eliteClass, EliteType type) {
		if (!hasEliteVariant(entityClass)) ELITE_MAP.put(entityClass, new HashMap<>());
		ELITE_MAP.get(entityClass).put(type, eliteClass);
	}
	
	public static boolean hasEliteVariant(Class<? extends Entity> entityClass) {
		return ELITE_MAP.containsKey(entityClass);
	}
	
	public static boolean hasEliteVariant(Class<? extends Entity> entityClass, EliteType type) {
		if (hasEliteVariant(entityClass)) return ELITE_MAP.get(entityClass).containsKey(type);
		return false;
	}
	
	/**
	 * Replaces an entity in the world with an elite variant of it if possible. 
	 * 
	 * @param entity The entity to replace
	 * @param type The type of elite to replace it with
	 * 
	 * @return whether or not the replacement was successful
	 */
	public static boolean replaceWithEliteVariant(Entity entity, EliteType type) {
		// If there is no elite variant, return false
		if (!hasEliteVariant(entity.getClass())) return false;
		// Otherwise, get the correct class for the elite
		Class<? extends Entity> eliteClass = ELITE_MAP.get(entity.getClass()).get(type);
		
		// Try to replace the entity with its elite variant
		try {
			// Create the new elite entity
			Entity elite = eliteClass.getDeclaredConstructor(World.class, double.class, double.class, double.class).newInstance(entity.worldObj, entity.posX, entity.posY, entity.posZ);
			// Spawn the new elite entity into the world
			entity.worldObj.spawnEntityInWorld(elite);
			// Kill the original entity
			entity.setDead();
			// Mark the replacement as successful
			return true;
		} 
		
		// If there isn't a constructor with (World, double, double, double) return false
		catch(Exception e) {
			return false;
		}
	}
}
