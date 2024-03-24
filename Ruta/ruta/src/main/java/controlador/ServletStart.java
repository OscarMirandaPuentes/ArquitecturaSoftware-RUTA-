package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Administrador;
import modelo.Jugador;
import modelo.Mazo;
import java.io.IOException;


@WebServlet("/start")
public class ServletStart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Administrador a;

    public ServletStart(){
        super();
        a = new Administrador();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        iniciar();

        // Estableces el atributo en la solicitud
        request.setAttribute("admin", a);

        // Obtiene el RequestDispatcher para el siguiente servlet
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jugar");

        // Reenvía la solicitud junto con los atributos al siguiente servlet
        dispatcher.forward(request, response);
        }

    public void iniciar() {
        Mazo mazo = new Mazo();
        a.iniciarJuego(mazo);
    }

    public void insertarJugador(int id, String nombre){
        if (id % 2 == 0) {
            Jugador jugador = a.getJ().crearJugador(nombre);
            a.getJ().getEquipo1().agregarJugador(jugador);
        } else {
            Jugador jugador = a.getJ().crearJugador(nombre);
            a.getJ().getEquipo2().agregarJugador(jugador);
        }
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("id"));
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");

        insertarJugador(id, nombre);

	}
}
