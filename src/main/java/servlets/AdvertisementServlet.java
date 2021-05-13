package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DAOAdvertisement;
import utils.ServletUtil;
import vo.Advertisement;
import vo.AppConstants;

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
            if (AppConstants.GET__ALL_ADVERTISEMENT.equals(req.getParameter("actionName"))) {
                getAllAdvertisement(req, resp);
            }
        } catch (Exception ex){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unknown Business Error");
        }

    }

    private void getAllAdvertisement(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<Integer, List<Advertisement>> servletUtil = new ServletUtil<>(req, resp);
        ObjectMapper mapper = new ObjectMapper();
        String json = servletUtil.parseJSONString();

        DAOAdvertisement daoAdvertisement = new DAOAdvertisement();
        List<Advertisement> advertisementList = null;
        try {
            advertisementList = daoAdvertisement.getAllAdvertisement();
        } catch (Exception ex) {
            System.out.println("Exception in LoginServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(advertisementList);
    }

}