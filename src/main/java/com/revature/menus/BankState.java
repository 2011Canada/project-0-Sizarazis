package com.revature.menus;

import java.util.Scanner;

public interface BankState {
		public String Display();
				
		public BankState HandleUserInput(String cmd);
}
