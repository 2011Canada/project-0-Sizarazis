package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;

public class EmployeeDAO implements IEmployeeDAO {
	
	static {
		Employee e = new Employee("stephen", "password123");
	}

}
