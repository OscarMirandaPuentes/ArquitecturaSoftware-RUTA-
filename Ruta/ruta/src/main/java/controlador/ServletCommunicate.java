package controlador;

import com.nimbusds.jose.shaded.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Administrador;
import modelo.Carta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;


@WebServlet("/communicate")
public class ServletCommunicate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Administrador a;

    public ServletCommunicate(){
        super();
        a = ServletStart.getAdministrador();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out=response.getWriter();
        List<String> envio = new ArrayList<>();
        List<String> pilas = new ArrayList<>();

        //Poner nulos
        if(a.getJ().checkPilas()){
            pilas.add(a.getJ().getEquipo1().pilaBatalla.cimaCarta().tipo);
            pilas.add(a.getJ().getEquipo1().pilaDistancia.cimaCarta().tipo);
            pilas.add(a.getJ().getEquipo1().pilaVelocidad.cimaCarta().tipo);

            pilas.add(a.getJ().getEquipo2().pilaBatalla.cimaCarta().tipo);
            pilas.add(a.getJ().getEquipo2().pilaDistancia.cimaCarta().tipo);
            pilas.add(a.getJ().getEquipo2().pilaVelocidad.cimaCarta().tipo);
            envio.addAll(pilas);
        }

        int id = Integer.parseInt(request.getParameter("id"));

        if (id == a.getJugadorActualPos()) {
            List<String> mano = new ArrayList<>();
            for(Carta carta: a.getJugadorActual().getMano()){
                mano.add(carta.tipo);
            }
            envio.addAll(mano);
        }

        // Convierte el envío a formato JSON
        Gson gson = new Gson();
        String envioJson = gson.toJson(envio);

        // Establece el tipo de contenido de la respuesta como JSON
        response.setContentType("application/json");

        // Escribe el envío JSON en el cuerpo de la respuesta
        System.out.println(envioJson);
        out.print(envioJson);
        out.flush();

    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
