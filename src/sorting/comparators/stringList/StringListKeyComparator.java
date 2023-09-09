package sorting.comparators.stringList;

import java.util.Comparator;
import java.util.Map;

public class StringListKeyComparator implements Comparator<Map.Entry<String, Float>>  {
	@Override
	public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
		return o1.getKey().compareTo(o2.getKey());
	}
}
