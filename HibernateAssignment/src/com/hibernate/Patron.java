package com.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Patron")
public class Patron {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name; 
	
	@Column(name="image")
	private byte[] image;
	
	public Patron() {}
	public Patron(int id, String name, byte[] image) {
		this.id = id;
		this.name = name;
		this.image = image;
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}

}
