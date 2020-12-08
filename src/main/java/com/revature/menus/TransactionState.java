package com.revature.menus;

import com.revature.models.User;

public class TransactionState implements BankState {
	User user;
	
	public TransactionState(User user) {
		this.user = user;
	}

	public String Display() {
		// TODO Auto-generated method stub
		return "\nYou are in the transaction state\n" + user;
	}

	public BankState HandleUserInput(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

}
