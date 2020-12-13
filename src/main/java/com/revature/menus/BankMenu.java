package com.revature.menus;

import java.util.Scanner;

public class BankMenu {
	private BankState state;
	Scanner in;
	
	public BankMenu() {
		this.state = new WelcomeState();
		in = new Scanner(System.in);
	}
	
	public void Run() {
		System.out.println(state.Display());
		
		BankState nextState = state.HandleUserInput(in.nextLine());
		ChangeState(nextState);
	}

	private void ChangeState(BankState nextState) {
		this.state = nextState;
	}
}
