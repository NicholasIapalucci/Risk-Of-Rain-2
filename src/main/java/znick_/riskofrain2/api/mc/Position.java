package znick_.riskofrain2.api.mc;

import net.minecraft.block.Block;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Position { 
	
	private double x;
	private double y;
	private double z;
	
	public Position(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position(Position pos) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}

	public Vec3 toVector() {
		return Vec3.createVectorHelper(this.x, this.y, this.z);
	}
	
	public Position toInt() {
		return new Position((int) this.x, (int) this.y, (int) this.z);
	}
	
	public void setBlock(World world, Block block) {
		world.setBlock(this.getIntX(), this.getIntY(), this.getIntZ(), block);
	}
	
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