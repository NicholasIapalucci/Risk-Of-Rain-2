package znick_.riskofrain2.util.helper;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import net.minecraft.util.Vec3;
import znick_.riskofrain2.api.mc.enums.Axis;

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
	
	public static Vec3 rotatePoint(Vec3 toRotate, Vec3 rotateAround, Axis axis, double angle) {
		Vec3 relativePoint = toRotate.subtract(rotateAround);
		
		double xNew = 0;
		double yNew = 0;
		double zNew = 0;
		
		switch(axis) {
		case X:
			xNew = relativePoint.xCoord;
			yNew = relativePoint.yCoord * Math.sin(angle) - relativePoint.zCoord * Math.sin(angle);
			zNew = relativePoint.yCoord * Math.sin(angle) + relativePoint.zCoord * Math.cos(angle);
			break;
		case Y:
			xNew = relativePoint.xCoord * Math.cos(angle) + relativePoint.zCoord * Math.sin(angle);
			yNew = relativePoint.yCoord;
			zNew = -relativePoint.xCoord * Math.sin(angle) + relativePoint.zCoord * Math.cos(angle);
			break;
		case Z:
			xNew = relativePoint.xCoord * Math.cos(angle) - relativePoint.yCoord * Math.sin(angle);
			yNew = relativePoint.xCoord * Math.sin(angle) + relativePoint.yCoord * Math.cos(angle);
			zNew = relativePoint.zCoord;
		}
		
		Vec3 rotated = Vec3.createVectorHelper(xNew, yNew, zNew);
		return rotated.addVector(rotateAround.xCoord, rotateAround.yCoord, rotateAround.zCoord);
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
	
	public static int[] primeFactorize(int n) {
		return Arrays.stream(factorize(n)).filter(i -> isPrime(i)).toArray();
	}
	
	public static boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) if (n % i == 0) return false;
		return true;
	}
	
	/**
	 * Returns an array containing the factors of a number in ascending order.
	 * 
	 * @param n The number to get the factors of.
	 */
	public static int[] factorize(int n) {
		Set<Integer> factors = new TreeSet<Integer>();
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				factors.add(i);
				factors.add(n/i);
			}
		}
		return factors.stream().mapToInt(i -> i).toArray();
	}
}
