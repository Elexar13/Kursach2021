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
                Advertisement advertisement = new Advertisement(rs.getInt("ad_id"), rs.getInt("user_id"), rs.getString("city"), rs.getString("type"), rs.getString("ad_title"), rs.getInt("price"), rs.getInt("count_of_room"), rs.getString("description"), rs.getString("ad_address"), rs.getString("status"), rs.getString("filepath"));
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
                " WHERE user_id IS NOT NULL AND status = 'approved'";
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
                Advertisement advertisement = new Advertisement(rs.getInt("ad_id"), rs.getInt("user_id"), rs.getString("city"), rs.getString("type"), rs.getString("ad_title"), rs.getInt("price"), rs.getInt("count_of_room"), rs.getString("description"), rs.getString("ad_address"), rs.getString("status"), rs.getString("filepath"));
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
                Advertisement advertisement = new Advertisement(rs.getInt("ad_id"), rs.getInt("user_id"), rs.getString("city"), rs.getString("type"), rs.getString("ad_title"), rs.getInt("price"), rs.getInt("count_of_room"), rs.getString("description"), rs.getString("ad_address"), rs.getString("status"), rs.getString("filepath"));
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

    public Integer getFavorByIdAndAdId(Integer userId, Integer advertisementId) {
        String sql = "SELECT * FROM user_favorites uf " +
                " WHERE uf.user_id = ? and uf.advertisement_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, advertisementId);
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getInt("advertisement_id");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
        return null;
    }

    public List<Advertisement> getAdvertisementsOfCurrentUser(Integer currentUserId) {
        List<Advertisement> advertisementList = new ArrayList<>();
        String sql = "SELECT * FROM advertisement WHERE user_id = ? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, currentUserId);
            rs = ps.executeQuery();
            while(rs.next()){
                Advertisement advertisement = new Advertisement(rs.getInt("ad_id"), rs.getInt("user_id"), rs.getString("city"), rs.getString("type"), rs.getString("ad_title"), rs.getInt("price"), rs.getInt("count_of_room"), rs.getString("description"), rs.getString("ad_address"), rs.getString("status"), rs.getString("filepath"));
                advertisementList.add(advertisement);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
        return advertisementList;
    }

    public Integer addAdvertisement(Advertisement advertisement) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO advertisement (user_id, type, city, price, count_of_room, ad_title, description, ad_address, status, filepath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING ad_id";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, advertisement.getUserId());
            ps.setString(2, advertisement.getType());
            ps.setString(3, advertisement.getCity());
            ps.setInt(4, advertisement.getPrice());
            ps.setInt(5, advertisement.getCountOfRoom());
            ps.setString(6, advertisement.getAdTitle());
            ps.setString(7, advertisement.getDescription());
            ps.setString(8, advertisement.getAddress());
            ps.setString(9, "waiting_for_approve");
            ps.setString(10, advertisement.getFilePath());
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ad_id");
            }
            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
    }

    public Integer updateAdvertisement(Advertisement advertisement) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE advertisement SET type = ?, city = ?, price = ?, count_of_room = ?, " +
                " ad_title = ?, description = ?, filepath = ?, ad_address = ? WHERE ad_id = ? RETURNING ad_id";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, advertisement.getType());
            ps.setString(2, advertisement.getCity());
            ps.setInt(3, advertisement.getPrice());
            ps.setInt(4, advertisement.getCountOfRoom());
            ps.setString(5, advertisement.getAdTitle());
            ps.setString(6, advertisement.getDescription());
            ps.setString(7, advertisement.getFilePath());
            ps.setString(8, advertisement.getAddress());
            ps.setInt(9, advertisement.getAdId());
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ad_id");
            }
            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
    }

    public void deleteAdvertisement(Integer advertisementId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM advertisement WHERE ad_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, advertisementId);
            rs = ps.executeQuery();

        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
    }

    public List<Advertisement> getAdminAdvertisements() {
        List<Advertisement> advertisementList = new ArrayList<>();
        String sql = "SELECT * FROM public.advertisement a" +
                " WHERE status = 'waiting_for_approve' ORDER BY create_date";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Advertisement advertisement = new Advertisement(rs.getInt("ad_id"), rs.getInt("user_id"), rs.getString("city"), rs.getString("type"), rs.getString("ad_title"), rs.getInt("price"), rs.getInt("count_of_room"), rs.getString("description"), rs.getString("ad_address"), rs.getString("status"), rs.getString("filepath"));
                advertisementList.add(advertisement);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
        return advertisementList;
    }

    public void approvePublication(Integer advertisementId) {
        List<Advertisement> advertisementList = new ArrayList<>();
        String sql = "UPDATE public.advertisement " +
                " SET status = 'approved' WHERE ad_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, advertisementId);
            rs = ps.executeQuery();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
    }

    public void rejectPublication(Integer advertisementId) {
        List<Advertisement> advertisementList = new ArrayList<>();
        String sql = "UPDATE public.advertisement " +
                " SET status = 'rejected' WHERE ad_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try{
            connection = super.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, advertisementId);
            rs = ps.executeQuery();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
    }
}
