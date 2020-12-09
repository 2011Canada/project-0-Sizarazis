package com.revature.launcher;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menus.BankMenu;

public class BankOfByteLauncher {

	public static Logger BoBLogger = LogManager.getLogger("com.revature.BoB");
	
	public static void main(String[] args) {
		BankMenu bankMenu = new BankMenu();
		
		while (true) {
			bankMenu.Run();
		}
	}
}
