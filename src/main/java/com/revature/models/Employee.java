package com.revature.models;

public class Employee extends User {
	private final int employee_id;
	private int customer_id;
	
	
	// If an employee is also a customer, they will be instantiated in the program as an employee with a customer_id set in the employee object.
	// the EmployeeTransactionState will ask the employee if they want to go to their customer services or employee services.
	
	
	public Employee(int user_id, int employee_id, String password) {
		super(user_id, password);
		
		this.employee_id = employee_id;
	}
	
	
	//getters and setters
	public int GetEmployeeId() {
		return this.employee_id;
	}
	
	
	// returns 0 if this employee has no associated customer account.
	public int GetCustomerId() {
		return this.customer_id;
	}
	
	public void SetCustomerId(int customer_id) {
		this.customer_id= customer_id;
	}


	//overrides
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + employee_id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employee_id != other.employee_id)
			return false;
		return true;
	}
	
	
	
	
}
