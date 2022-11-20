package com.claim.exception;
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1312654116559901453L;

	public ValidationException(String msg) {
		super(msg);
	}

	public ValidationException(Throwable e) {
		super(e);
	}

	public ValidationException(String msg, Throwable e) {
		super(msg, e);
	}

}