package com.stephenrazis.menus;

import java.util.Scanner;

import com.stephenrazis.models.Displayable;
import com.stephenrazis.repositories.CustomerDAO;
import com.stephenrazis.services.CustomerTransactionService;
import com.stephenrazis.services.ICustomerTransactionService;

public class BankMenu implements Displayable {
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
		state.HandleUserInput(in.next());
	}

	public void ChangeState() {
		// TODO
	}
	
	public String Display() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
