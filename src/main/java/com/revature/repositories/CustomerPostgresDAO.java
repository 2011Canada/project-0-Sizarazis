package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Customer;
import com.revature.util.ConnectionFactory;

public class CustomerPostgresDAO implements ICustomerDAO {
	
	static ConnectionFactory cf;
	
	public CustomerPostgresDAO() {
		cf = ConnectionFactory.getConnectionFactory();
	}
	
	public Customer SaveCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer UpdateCustomer(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> FindAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer FindCustomerById(String id) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = cf.getConnection();
		
		try {
			
		String sql = "SELECT * FROM users WHERE user_id = ?;";
		// FILL ?
		PreparedStatement ps = conn.prepareStatement(sql);
		conn.SOMETHING();
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//stop conn, look at Alec's code
		}
		if (SOMETHING.next())
		return null;
		
	}
	
}
