package znick_.riskofrain2.api.mc;

import net.minecraft.block.Block;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * Represents a set of (x, y, z) coordinates in the world.
 * 
 * @author zNick_
 */
public class Position { 
	
	private double x;
	private double y;
	private double z;
	
	/**
	 * Creates a new {@code Position} object.
	 * 
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param z The z-coordinate
	 */
	public Position(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Copies the given position into a new object
	 *
	 * @param pos The position to copy.
	 */
	public Position(Position pos) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}

	/**
	 * Converts the {@code Position} object into a Minecraft {@code Vec3} object.
	 * 
	 * @return The converted {@code Vec3} object.
	 */
	public Vec3 toVector() {
		return Vec3.createVectorHelper(this.x, this.y, this.z);
	}
	
	/**
	 * Returns a new {@code Position} object with the coordinates of this object casted to integers. Does
	 * not modify this object.
	 * 
	 * @return The new position object with integer coordinates.
	 */
	public Position toInt() {
		return new Position((int) this.x, (int) this.y, (int) this.z);
	}
	
	/**
	 * Sets the block at this position's coordinates.
	 * 
	 * @param world The world to set the block in 
	 * @param block The block to set
	 */
	public void setBlock(World world, Block block) {
		world.setBlock(this.getIntX(), this.getIntY(), this.getIntZ(), block);
	}
	
	/**
	 * Returns the block at this position's coordinates. 
	 * 
	 * @param world The world the block is in
	 */
	public Block getBlock(World world) {
		return world.getBlock(this.getIntX(), this.getIntY(), this.getIntZ());
	}
	
	public static String toString(double x, double y, double z) {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
	
	public int getIntX() {
		return (int) x;
	}
	
	public int getIntY() {
		return (int) y;
	}
	
	public int getIntZ() {
		return (int) z;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
}