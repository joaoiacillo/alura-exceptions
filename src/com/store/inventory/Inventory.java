package com.store.inventory;

import com.store.email.Email;

/**
 * Controls an item inventory in the store.
 * 
 * Before closing, at least one store or sell operation must be performed,
 * otherwise it throws a {@link NoOperationPerformedException}.
 * 
 * @author João Iacillo
 */
public class Inventory implements AutoCloseable {

	private Item item;
	private int quantity;
	private int maxQuantity;
	
	private int quantitySelled;
	
	private boolean operationPerformed = false;
	private boolean closed = false;
	
	public Inventory(Item item, int maxQuantity, int initialQuantity) {
		this.item = item;
		this.maxQuantity = maxQuantity;
		this.quantity = initialQuantity;
	}
	
	/**
	 * Stores a specific amount of the item.
	 * 
	 * When in force mode, if the amount provided surpasses the inventory max
	 * quantity, then it throws an {@link OutOfCapacityException}.
	 * 
	 * Can't be invoked when inventory is closed, otherwise throws
	 * {@link IllegalStateException}.
	 * 
	 * @param intendedStoreQuantity Amount of the item to store
	 * @throws OutOfStockException
	 */
	public void store(int intendedStoreQuantity, boolean force) throws OutOfCapacityException {
		if (this.closed) this.throwClosedInventoryException();
		
		this.operationPerformed = true;
		
		if ((force) && (this.quantity + intendedStoreQuantity > this.maxQuantity)) {
			throw new OutOfCapacityException("O estoque para o produto " + this.item.getName() + " não suporta inserir mais " + intendedStoreQuantity + " items. O máximo é " + this.maxQuantity + ".");
		}
		
		// How much space is yet possible to store
		int maxStoreQuantity = this.maxQuantity - this.quantity;
		
		int storeQuantity = Math.min(intendedStoreQuantity, maxStoreQuantity);
		
		int newQuantity = this.quantity + storeQuantity;
		
		if (intendedStoreQuantity > maxStoreQuantity) {
			Email.sendEmailToOrderDep(item.getName(), intendedStoreQuantity, storeQuantity);
		}
		
		this.quantity = newQuantity;
	}
	
	public void store(int intendedStoreQuantity) {
		this.store(intendedStoreQuantity, false);
	}
	
	/**
	 * Sells a specific amount of the item.
	 * 
	 * If the current inventory capacity is lower than the intended amount, it
	 * throws {@link OutOfStockException}.
	 * 
	 * Can't be invoked when inventory is closed, otherwise throws
	 * {@link IllegalStateException}.
	 * 
	 * @param amount Amount of the item to sell
	 * @throws OutOfStockException
	 */
	public void sell(int amount) throws OutOfStockException {
		if (this.closed) this.throwClosedInventoryException();
		
		this.operationPerformed = true;
		
		int newQuantity = this.quantity - amount;
		
		if (newQuantity < 0) {
			throw new OutOfStockException("O estoque para o produto " + this.item.getName() + " não suporta retirar " + amount + " items.");
		}
		
		this.quantitySelled += amount;
		this.quantity = newQuantity;
	}
	
	private void throwClosedInventoryException() {
		throw new IllegalStateException("O inventário está fechado.");
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getMaxQuantity() {
		return maxQuantity;
	}
	
	public boolean isClosed() {
		return this.closed;
	}
	
	public int getQuantitySelled() {
		return quantitySelled;
	}

	@Override
	public void close() throws NoOperationPerformedException {
		
		if (!this.operationPerformed) throw new NoOperationPerformedException(null);
		this.closed = true;
		
	}
	
}
