package com.cg.PaymentSystem.exception;

public class AccountException extends Exception {
	private static final long serialVersionUID = 1L;
	String msg;

	public AccountException(String msg) {
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
}
