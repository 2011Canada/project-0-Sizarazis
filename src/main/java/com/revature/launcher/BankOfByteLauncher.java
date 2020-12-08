package com.revature.launcher;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.CustomerNotFoundException;
import com.revature.menus.BankMenu;
import com.revature.models.Customer;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.ICustomerDAO;
import com.revature.services.CustomerTransactionService;

public class BankOfByteLauncher {

	public static Logger BoBLogger = LogManager.getLogger("com.revature.BoB");
	
	public static void main(String[] args) {
		ICustomerDAO customerDAO = new CustomerDAO();
		CustomerTransactionService cts = new CustomerTransactionService(customerDAO);
		BankMenu bankMenu = new BankMenu(cts);
		
		while (true) {
			bankMenu.Run();
		}
	}

}
