package znick_.riskofrain2.util.helper;

import java.util.Random;

public class ArrayHelper {

	public static <T> T randomElement(T[] ts) {
		return ts[new Random().nextInt(ts.length)];
	}
}
