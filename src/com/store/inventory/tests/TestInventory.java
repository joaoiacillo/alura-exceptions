package com.store.inventory.tests;

import com.store.inventory.Inventory;
import com.store.inventory.Item;
import com.store.inventory.OutOfCapacityException;
import com.store.inventory.OutOfStockException;

public class TestInventory {

	public static void main(String[] args) {
		
		Item item = new Item("Sabonete", 5.15, "Dove");
		
		Inventory inv = new Inventory(item, 500, 4);
		System.out.println(item.getName() + ": " + inv.getQuantity());
		
		try {
			inv.store(550);
			//System.out.println(item.getName() + ": " + inv.getQuantity());
		} catch (OutOfCapacityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			inv.sell(5);
			System.out.println(item.getName() + ": " + inv.getQuantity());
		} catch (OutOfStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.print("Fechando o inventário: ");
			inv.close();
			System.out.println(inv.isClosed());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			inv.sell(500);
		} catch (OutOfStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
