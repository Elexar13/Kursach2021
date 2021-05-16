package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DAOAdvertisement;
import utils.ServletUtil;
import vo.Advertisement;
import vo.AppConstants;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdvertisementServlet", value = "/advertisement")
public class AdvertisementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if (AppConstants.GET_FILTERED_ADVERTISEMENT.equals(req.getParameter("actionName"))) {
                getFilteredAdvertisement(req, resp);
            } else if (AppConstants.ADD_ADVERTISEMENT_TO_FAVORITES.equals(req.getParameter("actionName"))) {
                addAdvertisementToFavorite(req, resp);
            } else if (AppConstants.GET_FAVORITES_FOR_CURRENT_USER.equals(req.getParameter("actionName"))) {
                getFavoritesForCurrentUser(req, resp);
            } else if (AppConstants.REMOVE_FROM_FAVORITES_FOR_CURRENT_USER.equals(req.getParameter("actionName"))) {
                removeFromFavoritesForCurrentUser(req, resp);
            } else if (AppConstants.GET_FAVOR_BY_USER_ID_AND_AD_ID.equals(req.getParameter("actionName"))) {
                getFavorByIdAndAdId(req, resp);
            } else if (AppConstants.GET_ADVERTISEMENTS_OF_CURRENT_USER.equals(req.getParameter("actionName"))) {
                getAdvertisementsOfCurrentUser(req, resp);
            } else if (AppConstants.ADD_ADVERTISEMENTS.equals(req.getParameter("actionName"))) {
                addAdvertisement(req, resp);
            } else if (AppConstants.UPDATE_ADVERTISEMENTS.equals(req.getParameter("actionName"))) {
                updateAdvertisement(req, resp);
            }
            else if (AppConstants.DELETE_ADVERTISEMENTS.equals(req.getParameter("actionName"))) {
                deleteAdvertisement(req, resp);
            }
        } catch (Exception ex){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unknown Business Error");
        }

    }

    private void deleteAdvertisement(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<Advertisement, Integer> servletUtil = new ServletUtil<>(req, resp);
        ObjectMapper mapper = new ObjectMapper();
        String json = servletUtil.parseJSONString();

        Integer advertisementId = mapper.readValue(json, Integer.class);
        DAOAdvertisement daoAdvertisement = new DAOAdvertisement();
        try {
            daoAdvertisement.deleteAdvertisement(advertisementId);
        } catch (Exception ex) {
            System.out.println("Exception in AdvertisementServlet - doPost.");
            throw ex;
        }
        servletUtil.sendOK();
    }

    private void addAdvertisement(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<Advertisement, Integer> servletUtil = new ServletUtil<>(req, resp);
        ObjectMapper mapper = new ObjectMapper();
        String json = servletUtil.parseJSONString();

        Advertisement advertisement = mapper.readValue(json, Advertisement.class);
        DAOAdvertisement daoAdvertisement = new DAOAdvertisement();
        Integer advertisementId = null;
        try {
            advertisementId = daoAdvertisement.addAdvertisement(advertisement);
        } catch (Exception ex) {
            System.out.println("Exception in AdvertisementServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(advertisementId);
    }

    private void updateAdvertisement(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<Advertisement, Integer> servletUtil = new ServletUtil<>(req, resp);
        ObjectMapper mapper = new ObjectMapper();
        String json = servletUtil.parseJSONString();

        Advertisement advertisement = mapper.readValue(json, Advertisement.class);
        DAOAdvertisement daoAdvertisement = new DAOAdvertisement();
        Integer advertisementId = null;
        try {
            advertisementId = daoAdvertisement.updateAdvertisement(advertisement);
        } catch (Exception ex) {
            System.out.println("Exception in AdvertisementServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(advertisementId);
    }

    private void getAdvertisementsOfCurrentUser(HttpServletRequest req, HttpServletResponse resp) {
        ServletUtil<Integer, List<Advertisement>> servletUtil = new ServletUtil<>(req, resp);

        User user = (User) req.getSession().getAttribute("currentUser");
        Integer currentUserId = user.getUserId();

        DAOAdvertisement daoAdvertisement = new DAOAdvertisement();
        List<Advertisement> advertisementList = null;
        try {
            advertisementList = daoAdvertisement.getAdvertisementsOfCurrentUser(currentUserId);
            for (Advertisement advertisement : advertisementList){
                advertisement.setType(AppConstants.TYPE_MAP.get(advertisement.getType()));
            }
        } catch (Exception ex) {
            System.out.println("Exception in AdvertisementServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(advertisementList);
    }

    private void getFavorByIdAndAdId(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<Integer, Integer> servletUtil = new ServletUtil<>(req, resp);

        Integer userId = Integer.valueOf(req.getParameter("userId"));
        Integer advertisementId = Integer.valueOf(req.getParameter("adId"));

        DAOAdvertisement daoAdvertisement = new DAOAdvertisement();
        List<Advertisement> advertisementList = null;
        try {
            advertisementId = daoAdvertisement.getFavorByIdAndAdId(userId, advertisementId);
        } catch (Exception ex) {
            System.out.println("Exception in AdvertisementServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(advertisementId);
    }

    private void removeFromFavoritesForCurrentUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<Integer, Integer> servletUtil = new ServletUtil<>(req, resp);
        ObjectMapper mapper = new ObjectMapper();
        String json = servletUtil.parseJSONString();

        DAOAdvertisement daoAdvertisement = new DAOAdvertisement();
        Integer advertisementId = null;
        try {
            User user = (User) req.getSession().getAttribute("currentUser");
            Integer currentUserId = user.getUserId();
            advertisementId = mapper.readValue(json, Integer.class);
            advertisementId = daoAdvertisement.removeFromFavoritesForCurrentUser(currentUserId, advertisementId);
        } catch (Exception ex) {
            System.out.println("Exception in AdvertisementServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(advertisementId);
    }

    private void addAdvertisementToFavorite(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<Integer, Integer> servletUtil = new ServletUtil<>(req, resp);
        ObjectMapper mapper = new ObjectMapper();
        String json = servletUtil.parseJSONString();

        DAOAdvertisement daoAdvertisement = new DAOAdvertisement();
        Integer advertisementId = null;
        try {
            User user = (User) req.getSession().getAttribute("currentUser");
            Integer currentUserId = user.getUserId();
            advertisementId = mapper.readValue(json, Integer.class);
            advertisementId = daoAdvertisement.addAdvertisementToFavorite(currentUserId, advertisementId);
        } catch (Exception ex) {
            System.out.println("Exception in AdvertisementServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(advertisementId);
    }

    private void getFavoritesForCurrentUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<Integer, List<Advertisement>> servletUtil = new ServletUtil<>(req, resp);

        User user = (User) req.getSession().getAttribute("currentUser");
        Integer currentUserId = user.getUserId();

        DAOAdvertisement daoAdvertisement = new DAOAdvertisement();
        List<Advertisement> advertisementList = null;
        try {
            advertisementList = daoAdvertisement.getFavoritesForCurrentUser(currentUserId);
            for (Advertisement advertisement : advertisementList){
                advertisement.setType(AppConstants.TYPE_MAP.get(advertisement.getType()));
            }
        } catch (Exception ex) {
            System.out.println("Exception in AdvertisementServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(advertisementList);
    }

    public void getFilteredAdvertisement(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<Integer, List<Advertisement>> servletUtil = new ServletUtil<>(req, resp);
        ObjectMapper mapper = new ObjectMapper();
        String json = servletUtil.parseJSONString();

        Advertisement filterAdvertisement =  mapper.readValue(json, Advertisement.class);

        DAOAdvertisement daoAdvertisement = new DAOAdvertisement();
        List<Advertisement> advertisementList = null;
        try {
            advertisementList = daoAdvertisement.getFilteredAdvertisement(filterAdvertisement);
            for (Advertisement advertisement : advertisementList){
                advertisement.setType(AppConstants.TYPE_MAP.get(advertisement.getType()));
            }
        } catch (Exception ex) {
            System.out.println("Exception in AdvertisementServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(advertisementList);
    }

}