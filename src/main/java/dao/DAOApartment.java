package dao;

import vo.Address;
import vo.Apartment;
import vo.AppConstants;

import java.sql.*;

public class DAOApartment extends BaseDAO {

	public Apartment getApartment(Apartment apartment) throws SQLException {
		String sql = "SELECT * FROM apartment ap " +
				"INNER JOIN address ad ON ap.address_id = ad.address_id " +
				"WHERE apartment_id = ? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(AppConstants.DATABASE_URL, AppConstants.DATABASE_USER, AppConstants.DATABASE_PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, apartment.getApartmentId());
			rs = ps.executeQuery();
			if(rs.next()){
				Address address = new Address(rs.getString("city"), rs.getString("street"), rs.getInt("numberOfHouse"));
				return new Apartment(address, rs.getInt("userId"), rs.getString("forSale"), rs.getString("forRent"), rs.getString("status"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			super.finalizeDAO(connection, rs, ps);
		}
		return null;
	}
}
