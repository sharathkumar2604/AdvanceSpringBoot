package com.sharath.ghee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GheeEntity {

	@Id
	private int id;

	private String name;

	private String qty;

	private int price;

	public GheeEntity() {
	}

	public GheeEntity(String name, String qty, int price) {
		this.name = name;
		this.qty = qty;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getQty() {
		return qty;
	}

	public int getPrice() {
		return price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "GheeEntity [id=" + id + ", name=" + name + ", qty=" + qty + ", price=" + price + "]";
	}

}
