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
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			if(rs.next()){
				return new User(rs.getString("email"), rs.getString("password"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.finalizeDAO(connection, rs, ps);
		}
		return null;
	}
}
