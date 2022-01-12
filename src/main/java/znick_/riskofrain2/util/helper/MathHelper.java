package znick_.riskofrain2.util.helper;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

public class MathHelper {
	
	public static double map(double n, double lower, double higher, double newLower, double newHigher) {
		if (n >= lower && n <= higher) {
			
			double relativeN = n - lower;
			double interval = higher - lower;
			double percent = relativeN/interval;
			double newInterval = newHigher - newLower;
			double relativeNew = newInterval * percent;
			double newN = relativeNew + lower;
			
			return newN;
			
		} else throw new ArithmeticException("Number to map must be within the interval!");
	}
	
	public static double constrain(double a, double bottom, double top) {
		if (a > top) return top;
		if (a < bottom) return bottom;
		return a;
	}
	
	public static Vec3 multiplyVectorByScalar(Vec3 vector, double scalar) {
		vector.xCoord *= scalar;
		vector.yCoord *= scalar;
		vector.zCoord *= scalar;
		return vector;
	}
	
	public static double getDistanceBetweenEntities(Entity first, Entity second) {
		return Math.sqrt(Math.pow(first.posX - second.posX, 2) + Math.pow(first.posY - second.posY, 2) + Math.pow(first.posZ - second.posZ, 2));
	}
}
