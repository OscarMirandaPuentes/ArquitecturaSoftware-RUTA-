package com.ruta.rutaarch.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import com.ruta.rutaarch.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ruta.rutaarch.managers.UserManager;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/usuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletUsuario() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		UserManager mp=new UserManager();
		boolean result = false;
		HashGenerator hashGenerator = new HashGenerator();

		if(action.equals("CREATE")){
			User u = new User(request.getParameter("name"), request.getParameter("nickname"),
							  hashGenerator.generateSHA256(request.getParameter("password")), request.getParameter("email"));
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
