package controlador;
import modelo.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import entities.Carta;
import entities.Equipo;
import entities.Jugador;
import entities.PilaBatalla;
import entities.PilaDistancia;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import managers.BatallaManager;
import managers.CartaManager;
import managers.DistanciaManager;
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
		PilaBatalla pb = new PilaBatalla();
		Carta c1 = new Carta();
		Carta c2 = new Carta();
		c1.setNombre("gasolina");
		c1.setNombre("accidente");
		List <Carta> arreglo = new ArrayList<>(); 
		arreglo.add(c1);
		arreglo.add(c2);
	
		UserManager mp=new UserManager();
		CartaManager mc = new CartaManager();
		JugadorManager mpe = new JugadorManager();
		BatallaManager mb = new BatallaManager();
		Jugador j = new Jugador();
		//Equipo q = new Equipo();
		User u = new User();

		pb.setEquipoId(1);
		//pb.setCartas(arreglo);

		j.setNombre("andrea");
		j.setEquipo(null);
		u.setEmail("asd@ashdgasj.co");
		u.setPassword("123");
		u.setUsername("pablito");
		mp.createUser(u);
		mpe.createJugador(j);
		mc.createCarta(c1);
		mc.createCarta(c2);
		mb.createPilaBatalla(pb);

		
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
