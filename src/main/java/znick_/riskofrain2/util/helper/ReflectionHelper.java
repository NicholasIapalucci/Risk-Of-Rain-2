package znick_.riskofrain2.util.helper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ReflectionHelper {

	public static String toDetailedString(Object obj) {
		try {
			StringBuilder sb = new StringBuilder("\n");
			sb.append(obj.getClass().getSimpleName());
			sb.append("{");
			for (Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				sb.append('\n');
				sb.append('\t');
				sb.append(field.getName());
				sb.append(" = ");
				sb.append(field.get(obj));
			}
			sb.append('\n');
			sb.append("}");
			return sb.toString();
		} 
		
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean isEnum(Class clazz) {
		return Enum.class.isAssignableFrom(clazz);
	}
	
	public static Class<?> getTypeOfCollection(Field field) {
		return (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
	}
	
	public static Class[] getFieldGenerics(Field field) {
		if (field.getType().isPrimitive()) return new Class[0];
		Type[] types = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
		return Arrays.stream(types).map(type -> (Class) type).toArray(Class[]::new);
	}
}
