package znick_.riskofrain2.api.mc.data.nbt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import znick_.riskofrain2.api.mc.data.nbt.savers.Saver;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SavableMap {
	public Class<? extends Saver> keySaver() default Saver.class;
	public Class<? extends Saver> valueSaver() default Saver.class;
}
