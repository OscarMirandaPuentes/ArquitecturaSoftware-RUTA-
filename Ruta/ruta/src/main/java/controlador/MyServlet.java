package controlador;
import modelo.Model;

import java.io.IOException;
import java.io.PrintWriter;

import entities.Equipo;
import entities.Jugador;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import managers.JugadorManager;
import managers.UserManager;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/hola")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		UserManager mp=new UserManager();
		JugadorManager mpe = new JugadorManager();
		Jugador j = new Jugador();
		//Equipo q = new Equipo();
		User u = new User();
		j.setNombre("andrea");
		j.setEquipo(null);
		u.setEmail("asd@ashdgasj.co");
		u.setPassword("123");
		u.setUsername("pablito");
		mp.createUser(u);
		mpe.createJugador(j);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
