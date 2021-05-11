package dao;

import vo.AppConstants;

import java.sql.*;

public abstract class BaseDAO {
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void finalizeDAO(Connection conn, ResultSet rs, Statement ps) {
		try {
			if (conn != null && !conn.isClosed()){
				conn.close();
				if (ps != null && rs != null){
					ps.close();
					rs.close();
				}
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	protected Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(AppConstants.DATABASE_URL, AppConstants.DATABASE_USER, AppConstants.DATABASE_PASSWORD);
	}
}
