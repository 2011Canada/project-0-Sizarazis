package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

public class EmployeeDAO implements IEmployeeDAO {
	
	static List<Employee> employees = new ArrayList<Employee>();

	
	static {
		Employee employee = new Employee("stephen", "password123");
		employees.add(employee);
	}

	
	//TODO
	public Employee FindEmployeeById(String id) {
		for (Employee e : employees) {
			if (id.equals(e.GetId())) {
				return e;
			}
		}
		return null;
	}

}
