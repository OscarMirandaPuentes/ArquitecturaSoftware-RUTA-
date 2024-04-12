package controlador;
import modelo.Model;

import java.io.IOException;
import java.io.PrintWriter;

import entities.Carta;
import entities.Equipo;
import entities.Jugador;
import entities.Pila;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import managers.CartaManager;
import managers.EquipoManager;
import managers.JugadorManager;
import managers.PilaManager;
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
		PilaManager mpi = new PilaManager();
		CartaManager mc = new CartaManager();
		EquipoManager me = new EquipoManager();

		Carta ca = new Carta();
		Pila pi = new Pila();
		Jugador j = new Jugador();
		Equipo q = new Equipo();
		User u = new User();


		ca.setNombre("gasolina");
		j.setNombre("rrrrrr");
		j.setEquipo(null);
		u.setEmail("ddddd@ashdgasj.co");
		u.setPassword("123");
		u.setUsername("putmm");
		pi.setEquipo_id(q);
		pi.setCantidadCartas(2);
		pi.setTipoPila("distancia");

		mp.createUser(u);
		mpe.createJugador(j);
		mc.createCarta(ca);
		me.createEquipo(q);
		mpi.crearPila(pi);
		
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
