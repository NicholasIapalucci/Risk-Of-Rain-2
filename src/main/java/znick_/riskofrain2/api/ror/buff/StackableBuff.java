package znick_.riskofrain2.api.ror.buff;

public interface StackableBuff {
	
	public default int getMaxStackCount() {
		return Integer.MAX_VALUE;
	}
}
