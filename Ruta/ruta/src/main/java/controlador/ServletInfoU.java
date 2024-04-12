package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import managers.UserManager;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/info")
public class ServletInfoU extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletInfoU() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		UserManager mp = new UserManager();
		String result = null;
		Cookie[] cookies = request.getCookies();

		String email = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    email = cookie.getValue();
                    break;
                }
            }
        }
	
		if(action != null && action.equals("EMAIL")) {
			User u = mp.getUserByEmail(email);
			if(u != null) {
				result = u.getName();
			} else {
				result = "User not found for email: " + email;
			}
		} else {
			result = "Invalid action";
		}
	
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}
	
}
