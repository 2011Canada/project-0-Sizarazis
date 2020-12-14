package com.revature.launcher;


import java.sql.Timestamp;

//import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menus.BankMenu;
import com.revature.repositories.CustomerPostgresDAO;
import com.revature.repositories.EmployeePostgresDAO;
import com.revature.repositories.ICustomerDAO;
//import com.revature.util.ConnectionFactory;
import com.revature.repositories.IEmployeeDAO;

public class BankOfByteLauncher {

	public static Logger BoBLogger = LogManager.getLogger("com.revature.BoB");
	
	public static void main(String[] args) {
		BankMenu bankMenu = new BankMenu();
		
		// Test DB connection
//		ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
//		Connection conn = cf.getConnection();
		
//		ICustomerDAO cDAO = new CustomerPostgresDAO();
//		cDAO.GetNextCustomerId();
		
		while (true) {
			bankMenu.Run();
		}
	}
}
