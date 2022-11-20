package com.claim.exception;
public class NoResultsException extends Exception {

	private static final long serialVersionUID = -2982217595152639170L;

	public NoResultsException(String msg) {
		super(msg);
	}

	public NoResultsException(Throwable e) {
		super(e);
	}

	public NoResultsException(String msg, Throwable e) {
		super(msg, e);
	}

}