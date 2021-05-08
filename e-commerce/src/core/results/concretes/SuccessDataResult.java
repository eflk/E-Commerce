package core.results.concretes;

public class SuccessDataResult<T> extends DataResultBase<T> {

	public SuccessDataResult(T data) {
		super(true, data);
	}

	public SuccessDataResult(T data, String message) {
		super(true, data, message);
	}

	public SuccessDataResult(T data, int code, String message) {
		super(true, data, code, message);
	}

}
