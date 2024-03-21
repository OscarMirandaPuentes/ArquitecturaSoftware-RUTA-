package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Administrador;


@WebServlet("/jugar")
public class ServletPlay extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Administrador a;

    public ServletPlay (){
        super();
        a = new Administrador();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub
            PrintWriter out=response.getWriter();
            boolean ans;

            int id = (int) request.getAttribute("id");
            int carta = (int) request.getAttribute("posCarta");
            int accion = (int) request.getAttribute("accion");

            if (id % 2 == 0) {
                ans = a.getJ().getEquipo1().getJugadores().get(id).tipoAccion(carta, a.getJ().getEquipo1(), a.getJ().getEquipo2(), accion);
            } 
            else{
                ans = a.getJ().getEquipo2().getJugadores().get(id).tipoAccion(carta, a.getJ().getEquipo2(), a.getJ().getEquipo1(), accion);
            }

            out.println(ans);
            
        }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
