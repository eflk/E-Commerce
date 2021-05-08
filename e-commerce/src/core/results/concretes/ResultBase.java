package core.results.concretes;

import core.results.abstracts.Result;

public abstract class ResultBase implements Result {
	private int code;
	private String message;
	private boolean success;
	
	public ResultBase(boolean success) {
		this(success, 0, "");
	}
	
	public ResultBase(boolean success, String message) {
		this(success, 0, message);
	}
	
	public ResultBase(boolean success, int code, String message) {
		this.code = code;
		this.message = message;
		this.success = success;
	}

	@Override
	public boolean isSuccess() {
		return success;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	
	

}
