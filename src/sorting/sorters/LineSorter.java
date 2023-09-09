package sorting.sorters;

import sorting.comparators.stringList.StringListChainedComparator;
import sorting.comparators.stringList.StringListKeyComparator;
import sorting.comparators.stringList.StringListValueComparator;
import sorting.sorters.sorterInterface.Sorter;

import java.util.*;

public class LineSorter implements Sorter {
	private final Scanner scanner;
	private final ArrayList<String> lines;
	private final String sortingType;
	private HashMap<String, Float> elementOccurrence;
	private HashMap<String, Float> sortedElementOccurrence;

	public LineSorter(String sortingType){
		this.scanner = new Scanner(System.in);
		this.lines = new ArrayList<>();
		this.sortingType = sortingType;
		this.elementOccurrence = new HashMap<>();
		this.sortedElementOccurrence = new LinkedHashMap<>();
		readInput();
		sortLines();
		getOccurrences();
		printResult();
	}
	@Override
	public void readInput() {
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lines.add(line);
		}
	}

	@Override
	public void printResult() {
		switch (sortingType) {
			case "natural" -> printNaturalOrder();
			case "byCount" -> printOrderByCount();
		}
	}

	public void printNaturalOrder(){
		System.out.printf("Total lines: %s.\n", lines.size());
		System.out.print("Sorted data: ");
		for(String line:lines){
			System.out.print("\n" + line);
		}
	}

	public void printOrderByCount(){
		sortOccurrences();
		System.out.printf("Total lines: %s.", lines.size());
		for(Map.Entry<String, Float> aa:sortedElementOccurrence.entrySet()){
			System.out.printf("\n%s: %.0f time(s), %.0f%%", aa.getKey(), aa.getValue(), (aa.getValue() / lines.size()) * 100);
		}
	}
	private void getOccurrences(){
		for(String line:lines){
			if(elementOccurrence.containsKey(line)){
				continue;
			}
			elementOccurrence.put(line, (float) Collections.frequency(lines, line));
		}
	}
	private void sortOccurrences(){
		List<Map.Entry<String, Float>> list =
			new LinkedList<>(elementOccurrence.entrySet());
		Collections.sort(list, new StringListChainedComparator(
			new StringListValueComparator(),
			new StringListKeyComparator()
		));
		for(Map.Entry<String, Float> aa : list){
			sortedElementOccurrence.put(aa.getKey(), aa.getValue());
		}
	}

	private void sortLines(){
		Collections.sort(lines);
	}
}
