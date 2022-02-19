package znick_.riskofrain2.world.gen;

import net.minecraft.world.World;

public interface Structure {
	
	/**
	 * Generates the structure.
	 * 
	 * @param world The world to generate the structure in
	 * @param x The x-coordinate of the structure
	 * @param y The y-coordinate of the structure
	 * @param z The z-coordinate of the structure
	 * @param replace Whether or not the structure should replace existing blocks
	 */
	public abstract void generateStructure(World world, int x, int y, int z, boolean replace);
	
	/**
	 * Returns whether or not the structure can validly generate at the given position in the given world.
	 * 
	 * @param world The world to generate the structure in
	 * @param x The x-coordinate of the structure
	 * @param y The y-coordinate of the structure
	 * @param z The z-coordinate of the structure
	 */
	public abstract boolean canGenerateAt(World world, int x, int y, int z);
}
