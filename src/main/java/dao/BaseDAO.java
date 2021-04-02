package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
