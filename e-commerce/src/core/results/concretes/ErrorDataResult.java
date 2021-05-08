package core.results.concretes;

public class ErrorDataResult<T> extends DataResultBase<T> {

	public ErrorDataResult(T data) {
		super(false, data);
	}

	public ErrorDataResult(T data, String message) {
		super(false, data, message);
	}

	public ErrorDataResult(T data, int code, String message) {
		super(false, data, code, message);
	}

}
