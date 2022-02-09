package com.cg.PaymentSystem.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.cg.PaymentSYstem.exception.AccountBalanceException;
import com.cg.PaymentSYstem.exception.AccountCreateException;
import com.cg.PaymentSYstem.exception.AccountException;
import com.cg.PaymentSystem.model.Account;

@Repository
public class PaymentAccountDaoImpl implements PaymentAccountDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public int addAccount(Account acc) throws AccountCreationException {
		// logger.info("In addAccount method");
		int id = 0;
		Account holder = new Account();
		holder.setAccHolderName(acc.getAccHolderName());
		holder.setGender(acc.getGender());
		holder.setDate(acc.getDate());
		holder.setPanNumber(acc.getPanNumber());
		holder.setAccHolderAddr(acc.getAccHolderAddr());
		holder.setPhoneNo(acc.getPhoneNo());
		holder.setBalance(acc.getBalance());
		manager.persist(holder);

		id = getAccountId();
		if (id == 0)
			throw new AccountCreationException("Account creation failed!");
		return id;
	}

	public int getAccountId() throws AccountCreationException {
		// logger.info("In getAccountId method");
		int id = 0;
		String jpql = "Select max(acc.accNumber) from Account acc";
		Query query = manager.createQuery(jpql);
		id = (int) query.getSingleResult();
		return id;
	}

	@Override
	public int depositMoney(int accNumber, int money) throws AccountException {
		// logger.info("In depositMoney method");
		int bal = 0;
		boolean isValid = isIdPresent(accNumber);
		if (isValid == true) {
			String jpql = "Select acc.balance from Account acc where acc.accNumber = :accNum";
			Query query = manager.createQuery(jpql);
			query.setParameter("accNum", accNumber);
			bal = (Integer) query.getSingleResult();
			bal = bal + money;

			Account acc = manager.find(Account.class, accNumber);
			acc.setBalance(bal);

			jpql = "Select acc.balance from Account acc where acc.accNumber = :accNum";
			query = manager.createQuery(jpql);
			query.setParameter("accNum", accNumber);
			bal = (Integer) query.getSingleResult();
		} else
			throw new AccountException("Account doesn't exist!");
		return bal;
	}

	@Override
	public int withdrawMoney(int accNumber, int money) throws AccountException, AccountBalanceException {
		// logger.info("In withdrawMoney method");
		int minBalance = 500;
		int bal = 0;
		boolean isValid = isIdPresent(accNumber);
		if (isValid == true) {
			String jpql = "Select acc.balance from Account acc where acc.accNumber = :accNum";
			TypedQuery<Integer> query = manager.createQuery(jpql, Integer.class);
			query.setParameter("accNum", accNumber);
			bal = query.getSingleResult();
			if ((bal - money) < minBalance)
				throw new AccountBalanceException("Insufficient balance for this transaction");
			else
				bal = bal - money;

			Account acc = manager.find(Account.class, accNumber);
			acc.setBalance(bal);

			jpql = "Select acc.balance from Account acc where acc.accNumber = :accNum";
			query = manager.createQuery(jpql, Integer.class);
			query.setParameter("accNum", accNumber);
			bal = (Integer) query.getSingleResult();
		} else
			throw new AccountException("Account doesn't exist!");
		return bal;
	}

	@Override
	public String fundTransfer(int accNumber, int receiverAccNumber, int money)
			throws AccountException, AccountBalanceException {
		// logger.info("In fundTransfer method");
		withdrawMoney(accNumber, money);
		depositMoney(receiverAccNumber, money);
		return "fund transfer successful";
	}

	@Override
	public ArrayList<String> showTransactions(int accNumber) {
		// logger.info("In showTransaction method");
		return null;
	}

	@Override
	public int showBalance(int accNumber) throws AccountException {
		// logger.info("In showBalance method");
		int bal = 0;
		boolean isValid = isIdPresent(accNumber);
		if (isValid == true) {
			String jpql = "Select acc.balance from Account acc where acc.accNumber = :accNum";
			TypedQuery<Integer> query = manager.createQuery(jpql, Integer.class);
			query.setParameter("accNum", accNumber);
			bal = query.getSingleResult();
		} else
			throw new AccountException("Account doesn't exist!");
		return bal;
	}

	@Override
	public Account showDetails(int accNumber) throws AccountException {
		// logger.info("In showDetails method");
		Account acc;
		boolean isValid = isIdPresent(accNumber);
		if (isValid == true) {
			String jpql = "Select acc from Account acc where acc.accNumber = :accNum";
			TypedQuery<Account> query = manager.createQuery(jpql, Account.class);
			query.setParameter("accNum", accNumber);
			acc = query.getSingleResult();
		} else
			throw new AccountException("Account doesn't exist!");
		return acc;
	}

	public boolean isIdPresent(int accountId) {
		// logger.info("In isIdPresent method");
		String jpql = "Select acc.accNumber from Account acc";
		Query query = manager.createQuery(jpql);
		List<?> list = (List<?>) query.getResultList();
		if (list.contains(accountId))
			return true;
		else
			return false;
	}
}
