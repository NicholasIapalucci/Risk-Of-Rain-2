package znick_.riskofrain2.api.mc.data.nbt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation to signify that this field should not be written to or read from NBT with
 * {@link NBTHelper}.
 * 
 * @author zNick_
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Unsaved {

}
