package znick_.riskofrain2.api.mc.data.nbt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import znick_.riskofrain2.api.mc.data.nbt.savers.Saver;

/**
 * Marks that a field can be read and written to NBT with the given {@link Saver}.
 * 
 * @author zNick_
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SavableWith {
	Class<? extends Saver> saver();
}
