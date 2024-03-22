package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Administrador;
import modelo.Carta;


@WebServlet("/jugar")
public class ServletPlay extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Administrador a;

    public ServletPlay (){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Administrador atributo = (Administrador) request.getAttribute("admin");

        PrintWriter out=response.getWriter();
        boolean ans;
        Carta carta;

        if (atributo != null){
            this.a = atributo;
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        int posCarta = Integer.parseInt(request.getParameter("posCarta"));
        int accion = Integer.parseInt(request.getParameter("accion"));

        if ((Integer) id != null && (Integer)posCarta != null && (Integer) accion != null){
            if (id % 2 == 0) {
                ans = a.getJ().getEquipo1().getJugadores().get(id).tipoAccion(posCarta, a.getJ().getEquipo1(), a.getJ().getEquipo2(), accion);
                carta = a.getJ().getEquipo1().getJugadores().get(id).getMano().get(posCarta);
            }
            else{
                ans = a.getJ().getEquipo2().getJugadores().get(id).tipoAccion(posCarta, a.getJ().getEquipo2(), a.getJ().getEquipo1(), accion);
                carta = a.getJ().getEquipo2().getJugadores().get(id).getMano().get(posCarta);
            }

            if (ans){
                request.setAttribute("carta", carta);
                doPost(request,response);
            }

            out.println(ans);
        }

    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/communicate");
        dispatcher.forward(request, response);
	}

    public Administrador getA() {
        return a;
    }

    public void setA(Administrador a) {
        this.a = a;
    }
}
