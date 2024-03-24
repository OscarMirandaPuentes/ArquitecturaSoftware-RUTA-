package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Administrador;
import modelo.Carta;
import java.io.IOException;
//import java.io.PrintWriter;


@WebServlet("/communicate")
public class ServletCommunicate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Administrador a;

    public ServletCommunicate(){
        super();
        a = new Administrador();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            //PrintWriter out=response.getWriter();

            int id = (int) request.getAttribute("id");
            Carta carta = (Carta) request.getAttribute("carta");

            if (id % 2 == 0) {
                a.getJ().getEquipo1().getJugadores().get(id).tipoAccionAsync(carta, a.getJ().getEquipo1(), a.getJ().getEquipo2());
            } 
            else{
                a.getJ().getEquipo2().getJugadores().get(id).tipoAccionAsync(carta, a.getJ().getEquipo2(), a.getJ().getEquipo1());
            }

        }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
