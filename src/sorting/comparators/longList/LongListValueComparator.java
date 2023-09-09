package sorting.comparators.longList;

import java.util.Comparator;
import java.util.Map;

public class LongListValueComparator implements Comparator<Map.Entry<Long, Float>> {
	@Override
	public int compare(Map.Entry<Long, Float> o1, Map.Entry<Long, Float> o2) {
		return o1.getValue().compareTo(o2.getValue());
	}
}
