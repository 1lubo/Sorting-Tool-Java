package sorting.sorters;

import sorting.comparators.stringList.StringListKeyComparator;
import sorting.comparators.stringList.StringListChainedComparator;
import sorting.comparators.stringList.StringListValueComparator;
import sorting.sorters.sorterInterface.Sorter;

import java.util.*;

public class WordSorter implements Sorter {
	private final Scanner scanner;
	private final ArrayList<String> words;
	private final String sortingType;
	private HashMap<String, Float> elementOccurrence;
	private HashMap<String, Float> sortedElementOccurrence;

	public WordSorter(String sortingType){
		this.scanner = new Scanner(System.in);
		this.words = new ArrayList<>();
		this.sortingType = sortingType;
		this.elementOccurrence = new HashMap<>();
		this.sortedElementOccurrence = new LinkedHashMap<>();
		readInput();
		sortWords();
		getOccurrences();
		printResult();
	}
	@Override
	public void readInput() {
		while (scanner.hasNext()) {
			String word = scanner.next();
			words.add(word);
		}
	}

	@Override
	public void printResult() {
		switch (sortingType) {
			case "natural" -> printNaturalOrder();
			case "byCount" -> printOrderByCount();
		}
	}

	private void printNaturalOrder(){
		System.out.printf("Total words: %s.\n", words.size());
		System.out.print("Sorted data: ");
		for(String word:words){
			System.out.print("\n" + word);
		}
	}

	private void printOrderByCount(){
		sortOccurrences();
		System.out.printf("Total words: %s.", words.size());
		for(Map.Entry<String, Float> aa:sortedElementOccurrence.entrySet()){
			System.out.printf("\n%s: %.0f time(s), %.0f%%", aa.getKey(), aa.getValue(), (aa.getValue() / words.size()) * 100);
		}
	}

	private void getOccurrences(){
		for(String word:words){
			if(elementOccurrence.containsKey(word)){
				continue;
			}
			elementOccurrence.put(word, (float) Collections.frequency(words, word));
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
	private void sortWords(){ Collections.sort(words);	}

}
