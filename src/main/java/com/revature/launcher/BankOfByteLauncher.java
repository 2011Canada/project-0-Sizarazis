package com.revature.launcher;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menus.BankMenu;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.ICustomerDAO;
import com.revature.services.CustomerTransactionService;
import com.revature.services.ICustomerTransactionService;

public class BankOfByteLauncher {

	public static Logger BoBLogger = LogManager.getLogger("com.revature.BoB");
	
	public static void main(String[] args) {
		ICustomerDAO customerDAO = new CustomerDAO();
		ICustomerTransactionService cts = new CustomerTransactionService(customerDAO);
		BankMenu bankMenu = new BankMenu(cts);
		
		while (true) {
			bankMenu.Run();
		}
	}

}
