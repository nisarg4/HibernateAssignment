package com.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Bank")
public class Bank {
	
@Override
	public String toString() {
		return "Bank [id=" + id + ", name=" + name + "]";
	}
@Id
@Column(name="id")
private int id;

@Column(name="name")
private String name;

public Bank() {}

public Bank(int id, String name) {
	this.id = id;
	this.name = name;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
