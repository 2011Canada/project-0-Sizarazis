package com.revature.menus;

public interface BankState {
		public String Display();
				
		public BankState HandleUserInput(String cmd);
}
