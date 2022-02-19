package znick_.riskofrain2.util.helper;

import java.lang.reflect.Field;

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
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
