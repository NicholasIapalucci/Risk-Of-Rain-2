package znick_.riskofrain2.entity.elite.mobs;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import znick_.riskofrain2.entity.elite.EliteType;

public class EliteEntities {
	
	private static final Map<Class<? extends Entity>, Map<EliteType, Class<? extends Entity>>> ELITE_MAP = new HashMap<>();
	
	/**
	 * Registers the entity of having the elite variant of the given elite type with a mapping to the given
	 * elite class. If an elite class is not registered with this method, then {@link #hasEliteVariant(Class, EliteType)}
	 * will return false for the entity. Necessary for the elite variants to spawn in the world.
	 * 
	 * @param entityClass The class of the regular entity
	 * @param eliteClass The class of the elite entity
	 * @param type The elite type for the class
	 */
	public static void registerEliteVariant(Class<? extends Entity> entityClass, Class<? extends Entity> eliteClass, EliteType type) {
		if (!hasEliteVariant(entityClass)) ELITE_MAP.put(entityClass, new HashMap<>());
		ELITE_MAP.get(entityClass).put(type, eliteClass);
	}
	
	/**
	 * Returns whether or not the given entity class has an elite variant of any kind.
	 * 
	 * @param entityClass The entity's class to check
	 * 
	 * @return true iff there are elite versions of the entity
	 */
	public static boolean hasEliteVariant(Class<? extends Entity> entityClass) {
		return ELITE_MAP.containsKey(entityClass);
	}
	
	/**
	 * Returns whether or not the entity class has an elite variant of the given elite type. In theory
	 * should be functionally identical to {@link #hasEliteVariant(Class)} under the assumption that
	 * all entities with an elite variant have every type of elite.
	 * 
	 * @param entityClass The entity's class to check
	 * @param type The type of elite
	 * 
	 * @return true iff there is an elite variant with the given elite type for the given entity.
	 */
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
			Entity elite = eliteClass.getDeclaredConstructor(World.class).newInstance(entity.worldObj);
			// Move the elite entity to the position of the original entity
			elite.setPosition(entity.posX, entity.posY, entity.posZ);
			// Spawn the new elite entity into the world
			entity.worldObj.spawnEntityInWorld(elite);
			// Kill the original entity
			entity.setDead();
			// Mark the replacement as successful
			return true;
		} 
		
		// If there isn't a constructor with (World) return false
		catch(Exception e) {
			return false;
		}
	}
}
