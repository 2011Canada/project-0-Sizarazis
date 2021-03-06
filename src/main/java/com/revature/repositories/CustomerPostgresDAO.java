package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.util.ConnectionFactory;


public class CustomerPostgresDAO implements ICustomerDAO {
	
	static ConnectionFactory cf;
	
	public CustomerPostgresDAO() {
		cf = ConnectionFactory.getConnectionFactory();
	}
	
	
	// Adds the new user to the users table, the customers table, and sets up an account in the accounts table for them
	public void SaveCustomer(int customer_id, String password) {
		Connection conn = cf.getConnection();
		
		String sqlUser = "INSERT INTO bankofbyte.users (user_password) VALUES (?);";
		String sqlCustomer = "INSERT INTO bankofbyte.customer (user_id) VALUES (?);";
		String sqlAccount = "INSERT INTO bankofbyte.account (date_created, balance, is_validated, customer_id) VALUES (?, ?, ?, ?);";
		
		try {
			conn.setAutoCommit(false);
			
			PreparedStatement psUser = conn.prepareStatement(sqlUser);
			psUser.setString(1, password);
			psUser.executeUpdate();
			
			PreparedStatement psCustomer = conn.prepareStatement(sqlCustomer);
			int user_id = GetNextUserId() - 1;
			psCustomer.setInt(1, user_id);
			psCustomer.executeUpdate();
			
			PreparedStatement psAccount = conn.prepareStatement(sqlAccount);
			Timestamp now = new Timestamp(System.currentTimeMillis());
			psAccount.setTimestamp(1, now);
			psAccount.setDouble(2, 0.00);
			psAccount.setBoolean(3, false);
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public Customer FindCustomerById(int id) {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT * FROM bankofbyte.customer WHERE customer_id = ?;";
		
		Customer c = null;
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				int customer_id = rs.getInt("customer_id");
				c = new Customer(user_id, customer_id);
			}
			
			if (c != null) {
				c.setAccounts(GetAccounts(c));
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
		finally {
			//TODO: SEE WHAT ALEC DOES IN HIS CODE HERE
		}
		return c;
	}
	
	
	private List<Account> GetAccounts(Customer c) {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT a.account_id, a.balance, a.is_validated FROM bankofbyte.account a, bankofbyte.customer c WHERE c.customer_id = ? AND c.customer_id = a.customer_id;";
		
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, c.getCustomerId());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int account_id = rs.getInt("account_id");
				double amount = rs.getDouble("balance");
				boolean is_validated = rs.getBoolean("is_validated");
				Account a = new Account(account_id, amount, is_validated);
				accounts.add(a);
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
		return accounts;
	}

	
	public String GetPassword(int customer_id) {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT u.user_password FROM bankofbyte.users u, bankofbyte.customer c WHERE c.customer_id = ? AND c.user_id = u.user_id;";
		String password = "";		
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, customer_id);
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
	
	public int GetNextUserId() {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT MAX(user_id) FROM bankofbyte.users;";
		int nextId = 0;	
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				nextId = rs.getInt("max") + 1;
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

		return nextId;
	}
}
