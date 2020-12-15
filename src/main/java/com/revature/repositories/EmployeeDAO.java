package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;

public class EmployeeDAO implements IEmployeeDAO {
	
	static List<Employee> employees = new ArrayList<Employee>();

	
	static {
		Employee employee = new Employee(1, 11);
		employees.add(employee);
	}

	
	public Employee FindEmployeeById(int employee_id) {
		for (Employee e : employees) {
			if (employee_id == e.GetEmployeeId()) {
				System.out.println("Found employee");
				return e;
			}
		}
		return null;
	}


	public String GetPassword(int employee_id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int RegisterForAccount(Employee e) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Customer FindCustomerByUserId(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
