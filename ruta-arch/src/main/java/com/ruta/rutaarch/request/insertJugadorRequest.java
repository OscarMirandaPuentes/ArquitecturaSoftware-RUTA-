package com.ruta.rutaarch.request;

public class insertJugadorRequest {
    private String email;
    private int idPartida;
    private int numEquipo;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getIdPartida() {
        return idPartida;
    }
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }
    public int getNumEquipo() {
        return numEquipo;
    }
    public void setNumEquipo(int numEquipo) {
        this.numEquipo = numEquipo;
    }
    
}
