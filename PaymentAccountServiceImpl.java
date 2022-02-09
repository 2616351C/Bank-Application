package com.cg.PaymentSystem.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.PaymentSYstem.exception.AccountBalanceException;
import com.cg.PaymentSYstem.exception.AccountCreateException;
import com.cg.PaymentSYstem.exception.AccountException;
import com.cg.PaymentSystem.dao.PaymentAccountDaoImpl;
import com.cg.PaymentSystem.model.Account;

@Service
@Transactional
public class PaymentAccountServiceImpl implements PaymentAccountService {

	@Autowired
	PaymentAccountDaoImpl dao;

	@Override
	public int addAccount(Account acc) throws AccountCreationException {
		// logger.info("Inside addAccount in service");
		return dao.addAccount(acc);
	}

	@Override
	public int depositMoney(int accNumber, int money) throws AccountException {
		// logger.info("Inside depositMoney in service");
		return dao.depositMoney(accNumber, money);
	}

	@Override
	public int withdrawMoney(int accNumber, int money) throws AccountException, AccountBalanceException {
		// logger.info("Inside withdrawMoney in service");
		return dao.withdrawMoney(accNumber, money);
	}

	@Override
	public String fundTransfer(int accNumber, int receiverAccNumber, int money)
			throws AccountException, AccountBalanceException {
		// logger.info("Inside fundTransfer in service");
		return dao.fundTransfer(accNumber, receiverAccNumber, money);
	}

	@Override
	public ArrayList<String> showTransactions(int accNumber) {
		// logger.info("Inside showTransfer in service");
		return dao.showTransactions(accNumber);
	}

	@Override
	public int showBalance(int accNumber) throws AccountException {
		// logger.info("Inside showBalance in service");
		return dao.showBalance(accNumber);
	}

	@Override
	public Account showDetails(int accNumber) throws AccountException {
		// logger.info("Inside showDetails in service");
		return dao.showDetails(accNumber);
	}
}
