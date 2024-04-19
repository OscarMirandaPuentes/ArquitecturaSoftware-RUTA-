package controlador;

import modelo.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import entities.Carta;
import entities.Equipo;
import entities.Jugador;
import entities.Movimiento;
import entities.Partida;
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
import managers.MovimientoManager;
import managers.PartidaManager;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Crear los managers
		UserManager mp = new UserManager();
		JugadorManager mpe = new JugadorManager();
		PilaManager mpi = new PilaManager();
		CartaManager mc = new CartaManager();
		EquipoManager me = new EquipoManager();
		PartidaManager mpa = new PartidaManager();
		MovimientoManager mo = new MovimientoManager();

		// Crear una nueva partida
		Partida pa = new Partida();
		pa.setEstado("iniciada");
		pa.setJugadorTurno("1");

		// Persistir la partida
		mpa.createPartida(pa);

		// Crear un nuevo usuario
		User u = new User();
		u.setEmail("ddddd@ashdgasj.co");
		u.setPassword("123");
		u.setUsername("putmm");

		// Persistir el usuario
		mp.createUser(u);

		// Crear equipos y asociarlos a la partida
		Equipo q = new Equipo();
		Equipo q2 = new Equipo();

		List<Equipo> equipos = new ArrayList<>();
		equipos.add(q);
		equipos.add(q2);

		pa.setEquipos(equipos); // Asociar los equipos a la partida

		// Persistir los equipos y asociarlos a la partida
		q.setPartida(pa);
		me.createEquipo(q);

		q2.setPartida(pa);
		me.createEquipo(q2);

		// Crear jugadores y asociarlos a los equipos
		Jugador j = new Jugador();
		j.setNombre("Jugador 1");
		j.setEquipo(q);

		Jugador j2 = new Jugador();
		j2.setNombre("Jugador 2");
		j2.setEquipo(q2);

		// Persistir los jugadores
		mpe.createJugador(j);
		mpe.createJugador(j2);

		// Crear cartas
		Carta ca = new Carta();
		ca.setNombre("gasolina");
		ca.setTipoPila("distancia");

		Carta ca2 = new Carta();
		ca2.setNombre("100");
		ca2.setTipoPila("velocidad");

		// Persistir las cartas
		mc.createCarta(ca);
		mc.createCarta(ca2);

		// Crear pilas y asociarlas a los equipos
		Pila pi = new Pila();
		pi.setEquipo_id(q);
		pi.setTipoPila("distancia");

		Pila piv = new Pila();
		piv.setEquipo_id(q2);
		piv.setTipoPila("velocidad");

		// Persistir las pilas
		mpi.crearPila(pi);
		mpi.guardarCartaEnPila(pi, ca);
		mpi.crearPila(piv);
		mpi.guardarCartaEnPila(piv, ca2);

		// Crear un nuevo movimiento
		Movimiento mov = new Movimiento();
		mov.setJugador(j); // Establecer el jugador
		mov.setPartida(pa); // Establecer la partida
		mov.setCarta(ca); // Establecer la carta
		mov.setAccion("escoger"); // Establecer la acción
		mov.setTipoPilaAfectada("distancia"); // Establecer el tipo de pila afectada

		// Persistir el movimiento
		mo.createMovimiento(mov);

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
