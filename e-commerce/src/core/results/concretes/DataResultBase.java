package core.results.concretes;

import core.results.abstracts.DataResult;

public abstract class DataResultBase<T> extends ResultBase implements DataResult<T> {
	private T data;

	public DataResultBase(boolean success, T data) {
		this(success, data, 0, "");
	}

	public DataResultBase(boolean success, T data, String message) {
		this(success, data, 0, message);
	}

	public DataResultBase(boolean success, T data, int code, String message) {
		super(success, code, message);
		this.data = data;
	}

	@Override
	public T getData() {
		return data;
	}

}
