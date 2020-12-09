package com.revature.menus;

import java.util.Scanner;

public class BankMenu {
	BankState state;
	Scanner in;
	
	public BankMenu() {
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
