package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.util.ConnectionFactory;

public class EmployeePostgresDAO implements IEmployeeDAO {

static ConnectionFactory cf;
	
	public EmployeePostgresDAO() {
		cf = ConnectionFactory.getConnectionFactory();
	}
	
	public Employee FindEmployeeById(int employee_id) {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT * FROM bankofbyte.employee WHERE employee_id = ?;";
		
		Employee e = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, employee_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				e = new Employee(user_id, employee_id);
			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			if (conn != null) {
				try {
					System.out.println("Transaction is being rolled back.");
					conn.rollback();
				}
				catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		return e;
	}


	public String GetPassword(int employee_id) {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT u.user_password FROM bankofbyte.users u, bankofbyte.employee e WHERE e.employee_id = ? AND e.user_id = u.user_id;";
		String password = "";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, employee_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				password = rs.getString("user_password");
			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			if (conn != null) {
				try {
					//TODO: LOG THIS
					System.out.println("Transaction is being rolled back.");
					conn.rollback();
				}
				catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return password;
	}

	@Override
	public int RegisterForAccount(Employee e) {
		Connection conn = cf.getConnection();
		
		String sqlCustomer = "INSERT INTO bankofbyte.customer (user_id) VALUES (?);";
		String sqlAccount = "INSERT INTO bankofbyte.account (date_created, balance, is_validated, customer_id) VALUES (?, ?, ?, ?);";
		
		int customer_id = 0;
		
		try {
			conn.setAutoCommit(false);
			
			PreparedStatement psCustomer = conn.prepareStatement(sqlCustomer);
			int user_id = e.GetUserId();
			psCustomer.setInt(1, user_id);
			psCustomer.executeUpdate();
			
			PreparedStatement psAccount = conn.prepareStatement(sqlAccount);
			Timestamp now = new Timestamp(System.currentTimeMillis());
			
			Customer c = FindCustomerByUserId(user_id);
			if (c != null) {
				customer_id = c.getCustomerId();
			}
			else {
				customer_id = GetNextCustomerId();
			}
			
			psAccount.setTimestamp(1, now);
			psAccount.setDouble(2, 0.00);
			psAccount.setBoolean(3, true);
			psAccount.setInt(4, customer_id);
			psAccount.executeUpdate();
			
			conn.commit();
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			if (conn != null) {
				try {
					System.out.println("Transaction is being rolled back.");
					conn.rollback();
				}
				catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		
		return customer_id;
	}

	
	@Override
	public Customer FindCustomerByUserId(int user_id) {
	Connection conn = cf.getConnection();
		
		String sql = "SELECT * FROM bankofbyte.customer c, bankofbyte.users u WHERE u.user_id = ? AND u.user_id = c.user_id;";
		
		Customer c = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int customer_id = rs.getInt("customer_id");
				c = new Customer(user_id, customer_id);
			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			if (conn != null) {
				try {
					System.out.println("Transaction is being rolled back.");
					conn.rollback();
				}
				catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}

		return c;
	}
	
	@Override
	public int GetNextCustomerId() {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT MAX(customer_id) FROM bankofbyte.customer;";
		int nextId = 0;	
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				nextId = rs.getInt("max");
			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			if (conn != null) {
				try {
					//TODO: LOG THIS
					System.out.println("Transaction is being rolled back.");
					conn.rollback();
				}
				catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return nextId + 1;
	}

}
