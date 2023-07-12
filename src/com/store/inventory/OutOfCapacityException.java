package com.store.inventory;

/**
 * Signals that an inventory can no longer hold more items or a specific amount.
 * 
 * @author João Iacillo
 */
public class OutOfCapacityException extends RuntimeException {

	public OutOfCapacityException(String msg) {
		
		super(msg);
		
	}
	
}
