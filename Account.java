package com.cg.PaymentSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Account_info")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AccID")
	private int accNumber;

	@Column(name = "HolderName")
	private String accHolderName;

	@Column(name = "DOB")
	private String date;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "Address")
	private String accHolderAddr;

	@Column(name = "Balance")
	private int balance;

	@Column(name = "Contact")
	private long phoneNo;

	public Account() {

	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAccHolderAddr() {
		return accHolderAddr;
	}

	public void setAccHolderAddr(String accHolderAddr) {
		this.accHolderAddr = accHolderAddr;
	}

	public int getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Account(int accNumber, String accHolderName, String date, String gender, String panNumber,
			String accHolderAddr, int balance, long phoneNo) {
		super();
		this.accNumber = accNumber;
		this.accHolderName = accHolderName;
		this.date = date;
		this.gender = gender;
		this.accHolderAddr = accHolderAddr;
		this.balance = balance;
		this.phoneNo = phoneNo;
	}

}