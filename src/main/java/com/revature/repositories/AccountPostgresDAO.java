package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.ConnectionFactory;

public class AccountPostgresDAO implements IAccountDAO {
	static ConnectionFactory cf;
	
	
	public AccountPostgresDAO() {
		cf = ConnectionFactory.getConnectionFactory();
	}
	
	
	public void SaveAccount(Account account, int customer_id) {
		Connection conn = cf.getConnection();

		String sql = "INSERT INTO bankofbyte.account (date_created, balance, is_validated, customer_id) VALUES (?, ?, ?, ?);";
		
		try {
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			Timestamp now = new Timestamp(System.currentTimeMillis());
			
			ps.setTimestamp(1, now);
			ps.setDouble(2, 0.00);
			ps.setBoolean(3, false);
			ps.setInt(4, customer_id);
			
			ps.executeUpdate();
			
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

	
	public void UpdateAccount(Account account) {
		Connection conn = cf.getConnection();
		
		String sql = "UPDATE bankofbyte.account SET balance = ?, is_validated = ? WHERE account_id = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, account.GetBalance());
			ps.setBoolean(2, account.GetVerification());
			ps.setInt(3, account.GetAccountId());
			
			ps.executeUpdate();
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
	}

	
	public List<Account> FindAllAccounts() {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT * FROM bankofbyte.account;";

		List<Account> accounts = new ArrayList<Account>();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int account_id = rs.getInt("account_id");
				double balance = rs.getDouble("balance");
				boolean isValidated = rs.getBoolean("is_validated");
				
				Account a = new Account(account_id, balance, isValidated);
				accounts.add(a);
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
		
		return accounts;
	}

	
	public List<Account> FindAllCustomersAccounts(int customer_id) {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT * FROM bankofbyte.account a, bankofbyte.customer c WHERE c.customer_id = ? AND a.customer_id = c.customer_id;";

		List<Account> accounts = new ArrayList<Account>();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customer_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int account_id = rs.getInt("account_id");
				double balance = rs.getDouble("balance");
				boolean isValidated = rs.getBoolean("is_validated");
				
				Account a = new Account(account_id, balance, isValidated);
				accounts.add(a);
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
		
		return accounts;
	}

	
	public Account FindAccountById(int account_id) {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT * FROM bankofbyte.account WHERE account_id = ?;";

		Account a = null;
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account_id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				double balance = rs.getDouble("balance");
				boolean isValidated = rs.getBoolean("is_validated");
				a = new Account(account_id, balance, isValidated);
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
		
		return a;
	}

	
	public double GetAccountBalance(int account_id) {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT balance FROM bankofbyte.account WHERE account_id = ?;";
		double balance = 0;		
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account_id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				balance = rs.getDouble("balance");
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
		
		return balance;
	}

}
