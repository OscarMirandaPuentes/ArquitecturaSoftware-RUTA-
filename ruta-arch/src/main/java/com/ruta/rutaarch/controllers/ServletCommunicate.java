package com.ruta.rutaarch.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ruta.rutaarch.modelo.Administrador;
import com.ruta.rutaarch.modelo.Carta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.PrintWriter;
import com.google.gson.Gson;


@WebServlet("/api/communicate")
public class ServletCommunicate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Administrador a;

    public ServletCommunicate(){
        super();
        a = ServletStart.getAdministrador();
        System.out.println("comm" + a);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out=response.getWriter();
         HashMap<String, String> envio = new HashMap<>();

        //Poner nulos
        if(!a.getJ().getEquipo1().pilaBatalla.isEmpty()){
            envio.put("PBatallaE1", a.getJ().getEquipo1().pilaBatalla.cimaCarta().tipo);
        }
        if(!a.getJ().getEquipo2().pilaBatalla.isEmpty()){
            envio.put("PBatallaE2", a.getJ().getEquipo2().pilaBatalla.cimaCarta().tipo);
        }
        if(!a.getJ().getEquipo1().pilaDistancia.isEmpty()){
            envio.put("PDistanciaE1", a.getJ().getEquipo1().pilaDistancia.cimaCarta().tipo);
        }
        if(!a.getJ().getEquipo2().pilaDistancia.isEmpty()){
            envio.put("PDistanciaE2", a.getJ().getEquipo2().pilaDistancia.cimaCarta().tipo);
        }
        if(!a.getJ().getEquipo1().pilaVelocidad.isEmpty()){
            envio.put("PVelocidadE1", a.getJ().getEquipo1().pilaVelocidad.cimaCarta().tipo);
        }
        if(!a.getJ().getEquipo2().pilaVelocidad.isEmpty()){
            envio.put("PVelocidadE2", a.getJ().getEquipo2().pilaVelocidad.cimaCarta().tipo);
        }


        int id = Integer.parseInt(request.getParameter("id"));

        if (id == a.getActualPos()) {
            int i = 1;
            for(Carta carta: a.getJugadorActual().getMano()){
                envio.put("Carta " + i, carta.tipo);
                i++;
            }
        }

        // Convierte el envío a formato JSON
        Gson gson = new Gson();
        String envioJson = gson.toJson(envio);

        // Establece el tipo de contenido de la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Escribe el envío JSON en el cuerpo de la respuesta
        System.out.println(envioJson);
        out.print(envioJson);
        out.flush();

    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
