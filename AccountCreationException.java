package com.cg.PaymentSystem.exception;

public class AccountCreationException extends Exception {
	private static final long serialVersionUID = 1L;
	String msg;

	public AccountCreationException(String msg) {
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
}
