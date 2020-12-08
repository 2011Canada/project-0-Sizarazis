package com.stephenrazis.launcher;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stephenrazis.exceptions.CustomerNotFoundException;
import com.stephenrazis.menus.BankMenu;
import com.stephenrazis.models.Customer;
import com.stephenrazis.repositories.CustomerDAO;
import com.stephenrazis.repositories.ICustomerDAO;
import com.stephenrazis.services.CustomerTransactionService;

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
