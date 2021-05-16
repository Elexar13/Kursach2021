package dao;

import vo.Advertisement;
import vo.AppConstants;
import vo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOAdvertisement extends BaseDAO{

    public List<Advertisement> getAllAdvertisement() {
        List<Advertisement> advertisementList = new ArrayList<>();
        String sql = "SELECT * FROM public.advertisement a" +
                " INNER JOIN public.user u ON a.user_id = u.user_id ORDER BY create_date";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("username"), rs.getString("lastname"), rs.getString("password"), rs.getString("phone_number"), rs.getString("is_admin"));
                Advertisement advertisement = new Advertisement(rs.getInt("ad_id"), rs.getInt("user_id"), rs.getString("city"), rs.getString("type"), rs.getString("ad_title"), rs.getInt("price"), rs.getInt("count_of_room"), rs.getString("description"), rs.getString("status"), rs.getString("filepath"));
                advertisementList.add(advertisement);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
        return advertisementList;
    }

    public List<Advertisement> getFilteredAdvertisement(Advertisement filterAdvertisement) {
        List<Advertisement> advertisementList = new ArrayList<>();
        String sql = "SELECT * FROM public.advertisement a" +
                " WHERE user_id IS NOT NULL ";
        if (filterAdvertisement.getType() != null && AppConstants.TYPE_MAP.containsKey(filterAdvertisement.getType())){
            sql += "AND type = ? ";
        }
        if (filterAdvertisement.getCity() != null && AppConstants.CITY_MAP.containsKey(filterAdvertisement.getCity())){
            sql += "AND city = ? ";
        }
        if (filterAdvertisement.getPrice() != null){
            sql += "AND price <= ? ";
        }
        if (filterAdvertisement.getCountOfRoom() != null){
            sql += "AND count_of_room = ? ";
        }
        sql += "ORDER BY create_date";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            int i = 1;
            if (filterAdvertisement.getType() != null){
                ps.setString(i ,filterAdvertisement.getType());
                i++;
            }
            if (filterAdvertisement.getCity() != null){
                ps.setString(i ,filterAdvertisement.getCity());
                i++;
            }
            if (filterAdvertisement.getPrice() != null){
                ps.setInt(i ,filterAdvertisement.getPrice());
                i++;
            }
            if (filterAdvertisement.getCountOfRoom() != null){
                ps.setInt(i ,filterAdvertisement.getCountOfRoom());
                i++;
            }
            rs = ps.executeQuery();
            while(rs.next()){
                Advertisement advertisement = new Advertisement(rs.getInt("ad_id"), rs.getInt("user_id"), rs.getString("city"), rs.getString("type"), rs.getString("ad_title"), rs.getInt("price"), rs.getInt("count_of_room"), rs.getString("description"), rs.getString("status"), rs.getString("filepath"));
                advertisementList.add(advertisement);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
        return advertisementList;
    }

    public Integer addAdvertisementToFavorite(Integer userId, Integer advertisementId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user_favorites (user_id, advertisement_id) " +
                "VALUES (?, ?) RETURNING user_id";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, advertisementId);
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

    public List<Advertisement> getFavoritesForCurrentUser(Integer currentUserId) {
        List<Advertisement> advertisementList = new ArrayList<>();
        String sql = "SELECT * FROM user_favorites uf " +
                " INNER JOIN advertisement a on a.ad_id = uf.advertisement_id WHERE uf.user_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, currentUserId);
            rs = ps.executeQuery();
            while(rs.next()){
                Advertisement advertisement = new Advertisement(rs.getInt("ad_id"), rs.getInt("user_id"), rs.getString("city"), rs.getString("type"), rs.getString("ad_title"), rs.getInt("price"), rs.getInt("count_of_room"), rs.getString("description"), rs.getString("status"), rs.getString("filepath"));
                advertisementList.add(advertisement);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
        return advertisementList;
    }

    public Integer removeFromFavoritesForCurrentUser(Integer currentUserId, Integer advertisementId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM user_favorites WHERE user_id = ? AND advertisement_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, currentUserId);
            ps.setInt(2, advertisementId);
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
