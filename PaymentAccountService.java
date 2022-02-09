package com.cg.PaymentSystem.service;

import java.util.ArrayList;

import com.cg.PaymentSYstem.exception.AccountBalanceException;
import com.cg.PaymentSYstem.exception.AccountCreateException;
import com.cg.PaymentSYstem.exception.AccountException;
import com.cg.PaymentSystem.model.Account;

public interface PaymentAccountService {
	public int addAccount(Account acc) throws AccountCreationException;

	public int depositMoney(int accNumber, int money) throws AccountException;

	public int withdrawMoney(int accNumber, int money) throws AccountException, AccountBalanceException;

	public String fundTransfer(int accNumber, int receiverAccNumber, int money)
			throws AccountException, AccountBalanceException;

	public ArrayList<String> showTransactions(int accNumber);

	public int showBalance(int accNumber) throws AccountException;

	public Account showDetails(int accNumber) throws AccountException;
}
