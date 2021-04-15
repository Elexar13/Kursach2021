package servlets;

import dao.DAOUser;
import org.json.JSONException;
import org.json.JSONObject;
import vo.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("mail");
		String password = req.getParameter("pass");

//		User user = new User(email, password);
//		DAOUser daoUser = new DAOUser();
//		try {
//			user = daoUser.getUser(user);
//			RequestDispatcher dispatcher;
//			if (user != null){
//				dispatcher = req.getRequestDispatcher("main.jsp");
//				dispatcher.forward(req, resp);
//			} else {
//				dispatcher = req.getRequestDispatcher("error.jsp");
//				dispatcher.forward(req, resp);
//			}
//		} catch (SQLException throwable) {
//			System.out.println("Exception in DB: LoginServlet - doPost.");
//			throwable.printStackTrace();
//		} catch (Exception ex) {
//			System.out.println("Exception in LoginServlet - doPost.");
//			ex.printStackTrace();
//		}
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("mail", "email");
			jsonObject.put("password", "password");
		} catch (JSONException e) {
			e.printStackTrace();
		}
//		RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
//		dispatcher.forward(req, resp);
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setStatus(200);
		resp.getWriter().write(jsonObject.toString());

	}
}
