package sorting.sorters.sorterfactory;

import sorting.sorters.LongSorter;
import sorting.sorters.sorterInterface.Sorter;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SorterFactory {
	private static Map<String, Class<? extends Sorter>> sorterTypeMap = new HashMap<>();
	public static void addSorterTypeRelation(String type, Class<? extends Sorter> sorter) {
		sorterTypeMap.put(type, sorter);
	}
	public static Sorter build(String[] args) {
		if(Arrays.asList(args).contains("-inputFile") && Arrays.asList(args).contains("-outputFile")){
			int sortingTypeIndex = Arrays.asList(args).indexOf("-sortingType");
			int inputFileIndex = Arrays.asList(args).indexOf("-inputFile");
			int outputFileIndex = Arrays.asList(args).indexOf("-outputFile");
			return new LongSorter(args[sortingTypeIndex + 1], args[inputFileIndex + 1], args[outputFileIndex + 1]);
		} else if (Arrays.asList(args).contains("-inputFile") && !Arrays.asList(args).contains("-outputFile")) {
			int sortingTypeIndex = Arrays.asList(args).indexOf("-sortingType");
			int inputFileIndex = Arrays.asList(args).indexOf("-inputFile");
			return new LongSorter(args[sortingTypeIndex + 1], args[inputFileIndex + 1]);
		}
		HashMap<String, String> sorterDetails = getArguments(args);
		try{
			return sorterTypeMap.get(sorterDetails.get("type")).getConstructor(String.class).newInstance(sorterDetails.get("sorting"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private static HashMap<String, String> getArguments(String[] args) {
		String sortingType = "natural";
		HashMap<String, String> sorterDetails = new HashMap<>();
		int dataTypeIndex = Arrays.asList(args).indexOf("-dataType");
		String sorterType = args[dataTypeIndex + 1];
		if (Arrays.asList(args).contains("-sortingType")){
			int sortingTypeIndex = Arrays.asList(args).indexOf("-sortingType");
			sortingType = args[sortingTypeIndex + 1];
		}
		sorterDetails.put("type", sorterType);
		sorterDetails.put("sorting", sortingType);
		return sorterDetails;
	}
}
