package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		ObjectMapper mapper = new ObjectMapper();
		String json = sb.toString();
		User user =  mapper.readValue(json, User.class);

		DAOUser daoUser = new DAOUser();
		JSONObject jsonObject = new JSONObject();
		try {
			user = daoUser.getUser(user);
			try {
				jsonObject.put("user", user);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (SQLException throwable) {
			System.out.println("Exception in DB: LoginServlet - doPost.");
			throwable.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Exception in LoginServlet - doPost.");
			ex.printStackTrace();
		}

//		resp.setHeader("Access-Control-Allow-Origin", "*");
//		resp.setStatus(200);
//		resp.getWriter().write(jsonObject.toString());
		PrintWriter writer = resp.getWriter();
		resp.setStatus(HttpServletResponse.SC_OK);
		mapper.writeValue(writer, user);

	}
}
