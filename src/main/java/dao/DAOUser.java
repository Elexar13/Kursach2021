package dao;

import vo.AppConstants;
import vo.User;

import java.sql.*;

public class DAOUser extends BaseDAO {

	public User getUser(User user) throws SQLException {
		String sql = "SELECT * FROM public.user WHERE email = ? AND password = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
			connection = super.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			if(rs.next()){
				return new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("username"), rs.getString("lastname"), rs.getString("password"), rs.getString("phone_number"), rs.getString("is_admin"));
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			super.finalizeDAO(connection, rs, ps);
		}
		return null;
	}

	public Integer addUser(User user) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO public.user (email, username, lastname, password, is_admin, phone_number) " +
				"VALUES (?, ?, ?, ?, 'N', ?) RETURNING user_id";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
			connection = super.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getPhoneNumber());
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("user_id");
			}
			return null;
		} catch (SQLException | ClassNotFoundException ex) {
			throw ex;
		} finally {
			super.finalizeDAO(connection, rs, ps);
		}
	}
}
