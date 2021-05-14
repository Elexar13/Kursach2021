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
                Advertisement advertisement = new Advertisement(rs.getInt("ad_id"), user, rs.getString("city"), rs.getString("type"), rs.getString("ad_title"), rs.getInt("price"), rs.getInt("count_of_room"), rs.getString("description"), rs.getString("status"), rs.getString("filepath"));
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
                " INNER JOIN public.user u ON a.user_id = u.user_id WHERE user_id IS NOT NULL ";
        if (filterAdvertisement.getType() != null && AppConstants.TYPE_MAP.containsKey(filterAdvertisement.getType())){
            sql += "AND type = ? ";
        }
        if (filterAdvertisement.getCity() != null && AppConstants.CITY_MAP.containsKey(filterAdvertisement.getCity())){
            sql += "AND city = ? ";
        }
        if (filterAdvertisement.getPrice() != null){
            sql += "AND price = ? ";
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
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("username"), rs.getString("lastname"), rs.getString("password"), rs.getString("phone_number"), rs.getString("is_admin"));
                Advertisement advertisement = new Advertisement(rs.getInt("ad_id"), user, rs.getString("city"), rs.getString("type"), rs.getString("ad_title"), rs.getInt("price"), rs.getInt("count_of_room"), rs.getString("description"), rs.getString("status"), rs.getString("filepath"));
                advertisementList.add(advertisement);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            super.finalizeDAO(connection, rs, ps);
        }
        return advertisementList;
    }
}
