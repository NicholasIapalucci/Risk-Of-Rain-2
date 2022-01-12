package znick_.riskofrain2.util.helper;

public class StringHelper {

	public static String format(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 || s.charAt(i - 1) == '_') sb.append(Character.toUpperCase(s.charAt(i)));
			else if (s.charAt(i) == '_') sb.append(" ");
			else sb.append(s.charAt(i));
		}
		return sb.toString();
	}
}
