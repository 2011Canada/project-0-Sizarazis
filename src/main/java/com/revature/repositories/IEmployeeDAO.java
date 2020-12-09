package com.revature.repositories;

import com.revature.models.Employee;

public interface IEmployeeDAO {

	Employee FindEmployeeById(String id);

}
