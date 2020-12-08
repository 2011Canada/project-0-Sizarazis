package com.revature.menus;

import java.util.Scanner;

import com.revature.models.Displayable;
import com.revature.repositories.CustomerDAO;
import com.revature.services.CustomerTransactionService;
import com.revature.services.ICustomerTransactionService;

public class BankMenu {
	ICustomerTransactionService cts;
	BankState state;
	Scanner in;
	
	public BankMenu(ICustomerTransactionService cts) {
		this.cts = cts;
		this.state = new WelcomeState();
		in = new Scanner(System.in);
	}
	
	public void Run() {
		// TODO Auto-generated method stub
		System.out.println(state.Display());
		
		BankState nextState = state.HandleUserInput(in.next());
		ChangeState(nextState);
	}

	public void ChangeState(BankState nextState) {
		this.state = nextState;
	}
}
