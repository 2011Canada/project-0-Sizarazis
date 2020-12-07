package com.stephenrazis.menus;

import java.util.Scanner;

import com.stephenrazis.models.Displayable;
import com.stephenrazis.repositories.CustomerDAO;

public class BankMenu implements Displayable {
	CustomerDAO customerDAO;
	BankState state;
	Scanner in;
	
	public BankMenu(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
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
