package znick_.riskofrain2.util.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapHelper {

	/**
	 * Sorts a map by it's values.
	 * 
	 * @param map The map to sort
	 * @param <K> The type of keys in the map
	 * @param <V> The type of values in the map
	 * 
	 * @return The sorted map
	 * 
	 * @author Carter Page
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) result.put(entry.getKey(), entry.getValue());

        return result;
    }
}
