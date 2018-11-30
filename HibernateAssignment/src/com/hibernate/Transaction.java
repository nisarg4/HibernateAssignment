package com.hibernate;

public class Transaction {
	private  int id;
	private Account account;
	private double amount;
	private AccountType accountType;
	public Transaction(int id, Account account, double amount, AccountType accountType) {
		super();
		this.id = id;
		this.account = account;
		this.amount = amount;
		this.accountType = accountType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
}
