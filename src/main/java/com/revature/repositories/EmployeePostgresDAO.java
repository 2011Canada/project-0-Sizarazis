package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	//TODO: If I'm bored I can make a transaction model.
	public String GetTransactionLogs() {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT * FROM bankofbyte.transaction_logs;";
		
		String logs = "PREVIOUS TRANSACTIONS:";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("transaction_id");
				String type = rs.getString("transaction_type");
				double amount = rs.getDouble("amount");
				int from = rs.getInt("from_account");
				int to = rs.getInt("to_account");
				boolean isFinished = rs.getBoolean("is_finished");
				
				logs = logs + "\n" + "[transaction_id=" + id + ", transaction_type:" + type + ", amount:" + amount + ", from_account:" + from + ", to_account:" + to + ", is_finished:" + isFinished;
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
		return logs;
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

}
