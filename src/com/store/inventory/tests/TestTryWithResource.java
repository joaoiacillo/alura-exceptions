package com.store.inventory.tests;

import com.store.inventory.Inventory;
import com.store.inventory.Item;
import com.store.inventory.NoOperationPerformedException;
import com.store.inventory.OutOfCapacityException;

public class TestTryWithResource {
	
	private static void print(Item item, Inventory inv) {
		System.out.println("O " + item.getName() + " da " + item.getBrand() + " custa R$" + item.getPrice() + ".");
		System.out.println("Quantidade no estoque: " + inv.getQuantity() + "/" + inv.getMaxQuantity());
		System.out.println("Vendidos: " + inv.getQuantitySelled());
		System.out.println("=============\n");
	}

	public static void main(String[] args) {
		
		final Item item = new Item("Sapato", 560.58, "Olympikus");
		final int maxQuantity = 250;
		final int initialQuantity = 0;
		
		// For testing NoOperationPerfomedException catch: set this to false 
		final boolean executeSomething = true;
		
		try (Inventory inv = new Inventory(item, maxQuantity, initialQuantity)) {
			
			if (executeSomething) {
				
				inv.store(500);
				
				System.out.println("=============\n");
				print(item, inv);
				
			}
			
		} catch (NoOperationPerformedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
