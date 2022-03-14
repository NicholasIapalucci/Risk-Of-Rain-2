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
			
		} 
		
		else throw new ArithmeticException("Number to map must be within the interval!");
	}
	
	public static Vec3 addVectors(Vec3 vec1, Vec3 vec2) {
		return vec1.addVector(vec2.xCoord, vec2.yCoord, vec2.zCoord);
	}
	
	public static double constrain(double a, double bottom, double top) {
		return Math.max(bottom, Math.min(top, a));
	}
	
	public static Vec3 multiplyVectorByScalar(Vec3 vector, double scalar) {
		vector.xCoord *= scalar;
		vector.yCoord *= scalar;
		vector.zCoord *= scalar;
		return vector;
	}
}
