package com.hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Transaction")
public class Transaction {
	
	@Id
	@Column(name="id")
	private  int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="account_id")
	private Account account;
	
	@Column(name="amount")
	private double amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private AccountType accountType;
	
	public Transaction(){}
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
