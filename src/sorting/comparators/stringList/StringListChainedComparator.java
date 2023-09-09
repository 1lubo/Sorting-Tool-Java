package sorting.comparators.stringList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StringListChainedComparator implements Comparator<Map.Entry<String,Float>> {

	private List<Comparator<Map.Entry<String, Float>>> comparatorList;
	@SafeVarargs
	public StringListChainedComparator(Comparator<Map.Entry<String, Float>>... comparators){
		this.comparatorList = Arrays.asList(comparators);
	}
	@Override
	public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
		for(Comparator<Map.Entry<String, Float>> comparator : comparatorList) {
			int result = comparator.compare(o1, o2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
}
