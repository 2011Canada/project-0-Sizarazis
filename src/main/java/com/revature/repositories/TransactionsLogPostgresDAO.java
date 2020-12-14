package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionFactory;

public class TransactionsLogPostgresDAO implements ITransactionLogDAO {
	static ConnectionFactory cf;
	
	public TransactionsLogPostgresDAO() {
		cf = ConnectionFactory.getConnectionFactory();
	}
	
	
	@Override
	// If from_account or to_account are passed as -1, then it will insert a null value into the log
	public void InsertLog(String type, double amount, int from_account, int to_account) {
		Connection conn = cf.getConnection();
		
		String sql = "INSERT INTO bankofbyte.transaction_logs (transaction_type, amount, from_account, to_account) VALUES (?, ?, ?, ?);";
		
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			ps.setDouble(2, amount);
			
			if (from_account != -1) {
				ps.setInt(3, from_account);
			}
			else {
				ps.setNull(3, java.sql.Types.INTEGER);
			}
			if (to_account != -1) {
				ps.setInt(4, to_account);
			}
			else {
				ps.setNull(4, java.sql.Types.INTEGER);
			}
			
			ps.executeUpdate();
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
	}

	@Override
	//TODO: If I'm bored I can make a transaction model.
	public String GetAllLogs() {
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
				
				logs = logs + "\n" + "[transaction_id=" + id + ", transaction_type:" + type + ", amount:" + amount + ", from_account:" + from + ", to_account:" + to + "]";
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

}
