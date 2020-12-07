package com.stephenrazis.launcher;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stephenrazis.exceptions.CustomerNotFoundException;
import com.stephenrazis.menus.BankMenu;
import com.stephenrazis.models.Customer;
import com.stephenrazis.repositories.CustomerDAO;

public class BankOfByteLauncher {

	public static Logger BoBLogger = LogManager.getLogger("com.revature.BoB");
	
	public static void main(String[] args) throws CustomerNotFoundException {
		CustomerDAO cDAO = new CustomerDAO();
		BankMenu bankMenu = new BankMenu(cDAO);
		
		bankMenu.handleUserInput();
		
//		List<Customer> customers = cDAO.FindAllCustomers();
//		for (Customer c : customers) {
//			System.out.println(c.Display());
//		}
	}

}
