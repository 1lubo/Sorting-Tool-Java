package sorting;

import sorting.errors.ArgumentException;
import sorting.sorters.LineSorter;
import sorting.sorters.LongSorter;
import sorting.sorters.WordSorter;
import sorting.sorters.sorterfactory.SorterFactory;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {

	public static final String FILE_NAME_REGEX = "^[\\w,\\s-]+\\.[A-Za-z]{3}$";
	public static final ArrayList<String> VALID_ARGUMENTS = new ArrayList<>() {{
		add("-sortingType");
		add("-dataType");
		add("long");
		add("line");
		add("word");
		add("byCount");
		add("natural");
		add("-inputFile");
		add("-outputFile");
	}};
	public static void main(final String[] args) {

			if(Arrays.asList(args).contains("-sortingType") && (!Arrays.asList(args).contains("byCount")
				&& !Arrays.asList(args).contains("natural"))) {
				System.out.println("No sorting type defined!");
				return;
			}
			if(Arrays.asList(args).contains("-dataType") && (!Arrays.asList(args).contains("long")
				&& !Arrays.asList(args).contains("word") && !Arrays.asList(args).contains("line"))) {
				System.out.println("No data type defined!");
				return;
			}

			for(String arg: args){
				if(!VALID_ARGUMENTS.contains(arg) && !arg.matches(FILE_NAME_REGEX)){
					System.out.printf("\"%s\" is not a valid parameter. It will be skipped", arg);
				}
			}
			SorterFactory.addSorterTypeRelation("long", LongSorter.class);
			SorterFactory.addSorterTypeRelation("word", WordSorter.class);
			SorterFactory.addSorterTypeRelation("line", LineSorter.class);
			SorterFactory.build(args);
    }
}
