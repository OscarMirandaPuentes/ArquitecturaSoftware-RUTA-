package com.ruta.rutaarch.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ruta.rutaarch.modelo.Administrador;
import com.ruta.rutaarch.modelo.Carta;


@WebServlet("api/jugar")
public class ServletPlay extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Administrador a;

    public ServletPlay (){
        super();
        a = ServletStart.getAdministrador();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Administrador atributo = (Administrador) request.getAttribute("admin");
        System.out.println(atributo);

        if (atributo != null){
            this.a = atributo;
        }
        
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        boolean ans;
        int id = Integer.parseInt(request.getParameter("id"));
        int posCarta = Integer.parseInt(request.getParameter("posCarta"));
        int accion = Integer.parseInt(request.getParameter("accion"));

        if ((Integer) id != null && (Integer)posCarta != null && (Integer) accion != null){
            if (id % 2 == 0) {
                ans = a.getJ().getEquipo1().getJugadores().get(a.getJugadorActualPos()).tipoAccion(posCarta, a.getJ().getEquipo1(), a.getJ().getEquipo2(), accion);
            }
            else{
                ans = a.getJ().getEquipo2().getJugadores().get(a.getJugadorActualPos()).tipoAccion(posCarta, a.getJ().getEquipo2(), a.getJ().getEquipo1(), accion);
            }

            if (ans){
                a.validarPosicionJugador();
            }

            out.println(ans);
        }

	}
    public Administrador getA() {
        return a;
    }

    public void setA(Administrador a) {
        this.a = a;
    }
}
