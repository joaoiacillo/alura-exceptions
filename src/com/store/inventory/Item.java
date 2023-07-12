package com.store.inventory;

/**
 * Holds information about a single item in the store.
 * 
 * Items must be controlled by {@link Inventory}.
 * 
 * @author João Iacillo
 */
public class Item {

	private String name;
	private double price;
	private String brand;
	
	public Item(String name, double price, String brand) {
		this.name = name;
		this.price = price;
		this.brand = brand;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getBrand() {
		return brand;
	}
	
}
