package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DAOUser;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ServletUtil;
import vo.AppConstants;
import vo.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if (AppConstants.GET_USER.equals(req.getParameter("actionName"))) {
                getUser(req, resp);
            } else if (AppConstants.ADD_USER.equals(req.getParameter("actionName"))) {
                addUser(req, resp);
            }
        } catch (Exception ex){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unknown Business Error");
        }

    }

    private void getUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<User, User> servletUtil = new ServletUtil<>(req, resp);
        ObjectMapper mapper = new ObjectMapper();
        String json = servletUtil.parseJSONString();
        User user =  mapper.readValue(json, User.class);

        DAOUser daoUser = new DAOUser();
        try {
            user = daoUser.getUser(user);
        } catch (Exception ex) {
            System.out.println("Exception in LoginServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(user);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        ServletUtil<User, Integer> servletUtil = new ServletUtil<>(req, resp);
        ObjectMapper mapper = new ObjectMapper();
        String json = servletUtil.parseJSONString();
        User user = mapper.readValue(json, User.class);

        DAOUser daoUser = new DAOUser();
        Integer userId = null;
        try {
            userId = daoUser.addUser(user);
        } catch (Exception ex) {
            System.out.println("Exception in LoginServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(userId);
    }
}
