package core.results.abstracts;

public interface Result {
	boolean isSuccess();

	int getCode();

	String getMessage();
}
