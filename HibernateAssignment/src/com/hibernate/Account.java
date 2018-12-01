package com.hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Account")
public class Account {
	
	@Id
	@Column(name="id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bank_id")
	private Bank bank;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="patron_id")
	private Patron patron;
	
	public Account() {}
	public Account(int id, Bank bank, Patron patron) {
		super();
		this.id = id;
		this.bank = bank;
		this.patron = patron;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public Patron getPatron() {
		return patron;
	}
	public void setPatron(Patron patron) {
		this.patron = patron;
	}
	
	

}
