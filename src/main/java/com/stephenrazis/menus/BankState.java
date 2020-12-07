package com.stephenrazis.menus;

import java.util.Scanner;

public interface BankState {
		public String Display();
				
		public void NextState(BankState state);

		public void HandleUserInput(String cmd);
}
