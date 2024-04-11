package controlador;
import modelo.Model;

import java.io.IOException;
import java.io.PrintWriter;

import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import managers.UserManager;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/usuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		UserManager mp=new UserManager();
		boolean result = false;

		if(action.equals("CREATE")){
			User u = new User(request.getParameter("name"), request.getParameter("nickname"),
							  request.getParameter("password"), request.getParameter("email"));
			mp.createUser(u);
			result = true;
		}

		if(action.equals("LOGIN")){
			User u = mp.getUserByEmail(request.getParameter("email"));
			if (u.getPassword().equals(request.getParameter("password"))){
				result = true;
			}
			
		}
		// Configuramos la respuesta HTTP con el resultado booleano
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print("{\"success\": " + result + "}"); // Respondemos con un objeto JSON que contiene el resultado
		out.flush();
		out.close();
	}
}
