package core.results.concretes;

public class SuccessResult extends ResultBase {

	public SuccessResult() {
		super(true);
	}

	public SuccessResult(String message) {
		super(true, message);
	}

	public SuccessResult(int code, String message) {
		super(true, code, message);
	}

}
