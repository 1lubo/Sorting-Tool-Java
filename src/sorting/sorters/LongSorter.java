package sorting.sorters;

import sorting.comparators.longList.LongListChainedComparator;
import sorting.comparators.longList.LongListKeyComparator;
import sorting.comparators.longList.LongListValueComparator;
import sorting.comparators.stringList.StringListChainedComparator;
import sorting.comparators.stringList.StringListKeyComparator;
import sorting.comparators.stringList.StringListValueComparator;
import sorting.sorters.sorterInterface.Sorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class LongSorter implements Sorter {
	private final Scanner scanner;
	private final ArrayList<Long> numbers;
	private final String sortingType;
	private File outputFile;
	private HashMap<Long, Float> elementOccurrence;
	private HashMap<Long, Float> sortedElementOccurrence;

	public LongSorter(String sortingType){
		this.scanner = new Scanner(System.in);
		this.numbers = new ArrayList<>();
		this.sortingType = sortingType;
		this.elementOccurrence = new HashMap<>();
		this.sortedElementOccurrence = new LinkedHashMap<>();
		this.outputFile = null;
		readInput();
		sortNumbers();
		getOccurrences();
		printResult();
	}

	public LongSorter(String sortingType, String inputFile){
		this.scanner = openFile(inputFile);
		this.numbers = new ArrayList<>();
		this.sortingType = sortingType;
		this.elementOccurrence = new HashMap<>();
		this.sortedElementOccurrence = new LinkedHashMap<>();
		this.outputFile = null;
		readInput();
		sortNumbers();
		getOccurrences();
		printResult();
	}

	public LongSorter(String sortingType, String inputFile, String outputFile){
		this.scanner = openFile(inputFile);
		this.numbers = new ArrayList<>();
		this.sortingType = sortingType;
		this.elementOccurrence = new HashMap<>();
		this.sortedElementOccurrence = new LinkedHashMap<>();
		this.outputFile = new File(outputFile);
		readInput();
		sortNumbers();
		getOccurrences();
		writeResult();
	}
	@Override
	public void readInput() {
		while (scanner.hasNext()) {
			Long number = null;
			String nextInput = scanner.next();
			try {
				number = Long.parseLong(nextInput);
			} catch (NumberFormatException e) {
				System.out.printf("\"%s\" is not a valid long. It will be skipped.", nextInput);
				continue;
			}
			if(!Objects.isNull(number))
			numbers.add(number);
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
		System.out.printf("Total numbers: %s.\n", numbers.size());
		System.out.print("Sorted data: ");
		for(long number:numbers){
			System.out.print(number + " ");
		}
	}

	public void printOrderByCount(){
		sortOccurrences();
		System.out.printf("Total numbers: %s.", numbers.size());
		for(Map.Entry<Long, Float> aa:sortedElementOccurrence.entrySet()){
			System.out.printf("\n%s: %s time(s), %.0f%%", aa.getKey(), aa.getValue(), (aa.getValue() / numbers.size()) * 100);
		}
	}

	public void writeResult(){
		switch (sortingType) {
			case "natural" -> writeNaturalOrder();
			case "byCount" -> writeOrderByCount();
		}
	}

	public void writeNaturalOrder(){
		try{
			FileWriter myWriter = new FileWriter(outputFile);
			String firstLine = String.format("Total numbers: %s.\n", numbers.size());
			myWriter.write(firstLine);
			myWriter.write("Sorted data: ");
			for(long number:numbers){
				myWriter.write(number + " ");
			}
			myWriter.close();
		} catch (IOException e){
			System.out.println("An Error Occurred");
			e.printStackTrace();
		}
	}

	public void writeOrderByCount(){
		sortOccurrences();
		try{
			FileWriter myWriter = new FileWriter(outputFile);
			String firstLine = String.format("Total numbers: %s.\n", numbers.size());
			myWriter.write(firstLine);
			for(Map.Entry<Long, Float> aa:sortedElementOccurrence.entrySet()){
				String nextLine = String.format("\n%s: %s time(s), %.0f%%", aa.getKey(), aa.getValue(), (aa.getValue() / numbers.size()) * 100);
				myWriter.write(nextLine);
			}
			myWriter.close();
		} catch (IOException e){
			System.out.println("An Error Occurred");
			e.printStackTrace();
		}
	}
	private void sortNumbers(){
		Collections.sort(numbers);
	}

	private void getOccurrences(){
		for(long number:numbers){
			if(elementOccurrence.containsKey(number)){
				continue;
			}
			elementOccurrence.put(number, (float) Collections.frequency(numbers, number));
		}
	}

	private void sortOccurrences(){
		List<Map.Entry<Long, Float>> list =
			new LinkedList<>(elementOccurrence.entrySet());
		Collections.sort(list, new LongListChainedComparator(
			new LongListValueComparator(),
			new LongListKeyComparator()
		));
		for(Map.Entry<Long, Float> aa : list){
			sortedElementOccurrence.put(aa.getKey(), aa.getValue());
		}
	}

	public Scanner openFile(String fileName){
		Path path = Paths.get(fileName);
		File myFile = new File(((Path) path).toAbsolutePath().toString());
		try {
			return new Scanner(myFile);
		} catch (FileNotFoundException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;
		}
	}
}
