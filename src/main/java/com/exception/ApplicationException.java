package com.exception;

public class ApplicationException extends Exception {
Exception rootException = null;
	
	public ApplicationException(String msg) {
		super(msg);
	}
	public ApplicationException(Exception e) {
		super(e.getMessage());
		rootException = e;
	}

}
