package znick_.riskofrain2.api.mc.data.nbt;

public @interface SavableWith {
	
	Class<? extends SavableToNBT> saver();
}
