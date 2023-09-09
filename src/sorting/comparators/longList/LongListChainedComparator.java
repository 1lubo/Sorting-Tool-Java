package sorting.comparators.longList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class LongListChainedComparator implements Comparator<Map.Entry<Long,Float>> {

	private List<Comparator<Map.Entry<Long, Float>>> comparatorList;
	@SafeVarargs
	public LongListChainedComparator(Comparator<Map.Entry<Long, Float>>... comparators){
		this.comparatorList = Arrays.asList(comparators);
	}
	@Override
	public int compare(Map.Entry<Long, Float> o1, Map.Entry<Long, Float> o2) {
		for(Comparator<Map.Entry<Long, Float>> comparator : comparatorList) {
			int result = comparator.compare(o1, o2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
}
