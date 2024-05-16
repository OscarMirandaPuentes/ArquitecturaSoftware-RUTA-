package com.ruta.rutaarch.request;

public class JugadaRequest {

    long idPartida;
    int idPlayer;
    int accion;
    int posCarta;
    
    public long getIdPartida() {
        return idPartida;
    }
    public int getIdPlayer() {
        return idPlayer;
    }
    public int getAccion() {
        return accion;
    }
    public int getPosCarta() {
        return posCarta;
    }
    public void setIdPartida(long idPartida) {
        this.idPartida = idPartida;
    }
    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }
    public void setAccion(int accion) {
        this.accion = accion;
    }
    public void setPosCarta(int posCarta) {
        this.posCarta = posCarta;
    }

}
