package com.store.inventory;

/**
 * Signals that a class was created but no operation was performed in it.
 * @author João Iacillo
 */
public class NoOperationPerformedException extends Exception {

	public NoOperationPerformedException(String msg) {

		super(msg);
		
	}
	
}
