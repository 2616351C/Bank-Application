package com.cg.PaymentSystem.exception;

public class AccountBalanceException extends Exception {
	private static final long serialVersionUID = 1L;
	String msg;

	public AccountBalanceException(String msg) {
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
}