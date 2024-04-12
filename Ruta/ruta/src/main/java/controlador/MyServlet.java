package controlador;
import modelo.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
		
		UserManager mp=new UserManager();
		JugadorManager mpe = new JugadorManager();
		PilaManager mpi = new PilaManager();
		CartaManager mc = new CartaManager();
		EquipoManager me = new EquipoManager();

		Carta ca = new Carta();
		Carta ca2 = new Carta();
		Pila pi = new Pila();
		Pila piv = new Pila();
		Jugador j = new Jugador();
		Equipo q = new Equipo();
		User u = new User();


		ca.setNombre("gasolina");
		ca.setTipoPila("distancia");
		ca2.setNombre("100");
		ca2.setTipoPila("velocidad");
		j.setNombre("rrrrrr");
		j.setEquipo(null);
		u.setEmail("ddddd@ashdgasj.co");
		u.setPassword("123");
		u.setUsername("putmm");
		pi.setEquipo_id(q);
		pi.setTipoPila("distancia");
		piv.setEquipo_id(q);
		piv.setTipoPila("velocidad");

		mp.createUser(u);
		mpe.createJugador(j);
		mc.createCarta(ca);
		mc.createCarta(ca2);
		me.createEquipo(q);
		mpi.crearPila(pi);
		mpi.guardarCartaEnPila(pi, ca);
		mpi.crearPila(piv);
		mpi.guardarCartaEnPila(piv, ca2);
		
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
