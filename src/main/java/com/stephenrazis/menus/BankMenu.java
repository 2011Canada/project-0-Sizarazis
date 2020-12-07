package com.stephenrazis.menus;

import com.stephenrazis.models.Displayable;
import com.stephenrazis.repositories.CustomerDAO;

public class BankMenu implements Displayable {
	CustomerDAO cDAO;
	InteractionState state;
	
	public BankMenu(CustomerDAO cDAO) {
		this.cDAO = cDAO;
		this.state = new WelcomeState();
	}
	
	private void ChangeState(InteractionState state) {
		this.state = state;
	}
	
	public void handleUserInput() {
		// TODO Auto-generated method stub
		
	}

	public String Display() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
