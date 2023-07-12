package com.store.email;

public class Email {

	public static void sendEmailToOrderDep(String itemName, int intendedStoreQuantity, int actualStoreQuantity) {
		System.out.println("Cara equipe de encomenda, ");
		System.out.println("Não é possível armazenar todos os " + intendedStoreQuantity + " do item \"" + itemName + "\".");
		System.out.println("Apenas " + actualStoreQuantity + " foram armazenados.");
		System.out.println("Grato,\nEquipe de Desenvolvimento de Software.");
	}
	
}
