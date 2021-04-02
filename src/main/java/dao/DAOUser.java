package dao;

import vo.AppConstants;
import vo.User;

import java.sql.*;

public class DAOUser extends BaseDAO {

	public User getUser(User user) throws SQLException {
		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(AppConstants.DATABASE_URL, AppConstants.DATABASE_USER, AppConstants.DATABASE_PASSWORD);
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				return new User(rs.getString("email"), rs.getString("password"));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			finalizeDAO(connection, rs, ps);
		}
		return null;
	}
}
