package core.results.concretes;

public class ErrorResult extends ResultBase {

	public ErrorResult() {
		super(false);
	}

	public ErrorResult(String message) {
		super(false, message);
	}

	public ErrorResult(int code, String message) {
		super(false, code, message);
	}

}
