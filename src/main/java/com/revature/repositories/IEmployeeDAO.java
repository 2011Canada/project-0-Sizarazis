package com.revature.repositories;

import com.revature.models.Customer;
import com.revature.models.Employee;

public interface IEmployeeDAO {

	Employee FindEmployeeById(int employee_id);
		
	String GetPassword(int employee_id);

	int RegisterForAccount(Employee e);
	
	Customer FindCustomerByUserId(int user_id);

	int GetNextCustomerId();
}
