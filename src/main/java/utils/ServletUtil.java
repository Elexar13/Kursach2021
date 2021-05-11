package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ServletUtil<IN,OUT> {
    private final String ACTION_NAME = "actionName";
    protected final HttpServletRequest req;
    protected final HttpServletResponse resp;
    private final String actionName;

    public ServletUtil(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        this.actionName = req.getParameter(ACTION_NAME);
    }

    public String parseJSONString() throws Exception {
        try (BufferedReader reader = this.req.getReader()) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void sendObject(OUT obj) {
        resp.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        try {
            PrintWriter writer = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(writer, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
