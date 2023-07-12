package com.store.inventory;

/**
 * Signals that an inventory can no longer sell more items or a specific amount.
 * 
 * @author João Iacillo
 */
public class OutOfStockException extends RuntimeException {

	public OutOfStockException(String msg) {
		
		super(msg);
		
	}
	
}
