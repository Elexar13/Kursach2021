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
import javax.servlet.http.HttpSession;
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
            } else if (AppConstants.GET_CURRENT_USER.equals(req.getParameter("actionName"))) {
                getCurrentUser(req, resp);
            } else if (AppConstants.LOG_OUT.equals(req.getParameter("actionName"))) {
                logOut(req, resp);
            } else if (AppConstants.GET_USER_BY_ID.equals(req.getParameter("actionName"))) {
                getUserById(req, resp);
            }
        } catch (Exception ex){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unknown Business Error");
        }

    }

    private void getUserById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<Integer, User> servletUtil = new ServletUtil<>(req, resp);
        Integer userId = Integer.valueOf(req.getParameter("userId"));

        DAOUser daoUser = new DAOUser();
        User user = null;
        try {
            user = daoUser.getUserById(userId);
        } catch (Exception ex) {
            System.out.println("Exception in UserServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(user);
    }

    private void getCurrentUser(HttpServletRequest req, HttpServletResponse resp) {
        ServletUtil<User, User> servletUtil = new ServletUtil<>(req, resp);
        User user = (User) req.getSession().getAttribute("currentUser");
        servletUtil.sendObject(user);
    }

    private void getUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletUtil<User, User> servletUtil = new ServletUtil<>(req, resp);
        ObjectMapper mapper = new ObjectMapper();
        String json = servletUtil.parseJSONString();
        User userIn =  mapper.readValue(json, User.class);

        DAOUser daoUser = new DAOUser();
        User userOut = null;
        try {
            userOut = daoUser.getUser(userIn);
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", userOut);
        } catch (Exception ex) {
            System.out.println("Exception in UserServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(userOut);
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
            System.out.println("Exception in UserServlet - doPost.");
            throw ex;
        }
        servletUtil.sendObject(userId);
    }

    private void logOut(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("currentUser");
    }
}
