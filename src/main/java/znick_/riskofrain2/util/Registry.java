package znick_.riskofrain2.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Registry<T> {

    private static final Set<Registry> REGISTRIES = new LinkedHashSet<>();

    public Registry() {
        REGISTRIES.add(this);
    }

    public abstract Method getRegistryMethod();
    protected abstract Object[] getRegistryMethodArgs(T obj);

    protected Object getRegistryMethodObject() {
        return null;
    }

    public void register() {
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                this.getRegistryMethod().invoke(this.getRegistryMethodObject(), this.getRegistryMethodArgs((T) field.get(null)));
            } catch(Exception e) {
                continue;
            }
        }
    }
    
    public static void registerAll() {
        for (Registry registry : REGISTRIES) registry.register();
    }
}