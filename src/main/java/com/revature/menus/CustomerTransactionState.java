package com.revature.menus;

import com.revature.models.Customer;

public class CustomerTransactionState implements BankState {
	Customer customer;
	
	public CustomerTransactionState(Customer customer) {
		this.customer = customer;
	}

	public String Display() {
		// TODO Auto-generated method stub
		return "\nYou are in the transaction state\n" + customer;
	}

	public BankState HandleUserInput(String cmd) {
		// TODO Auto-generated method stub
		
		if (cmd.equals("exit")) {
			Logout();
		}
		return null;
	}
	
	public void Logout() {
		System.out.println("\nConnection closed.");
		System.exit(0);
	}

}
