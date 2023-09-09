package sorting.errors;

public class ArgumentException extends RuntimeException {
	public ArgumentException(String argumentType) { super(String.format("No %s type defined!", argumentType));}
}
