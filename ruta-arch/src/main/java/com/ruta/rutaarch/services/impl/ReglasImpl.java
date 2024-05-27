package com.ruta.rutaarch.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ruta.rutaarch.modelo.CartasMap;

@Service
public class ReglasImpl {

    CartasMap cartasMap = new CartasMap();

    public boolean verificarJugada(String carta, String topeVel, String topeDis, String topeBat, List<String> seguridad){
        String tipoCarta = cartasMap.getCartasPorTipo().get(carta);

        if(tipoCarta.equals("Peligro")){
            if(revisarSeguridad(carta, seguridad)){
                return false;
            }
                return true;
        }
        else if(tipoCarta.equals("Seguridad")){
            if (revisarSeguridad(carta, seguridad)) {
                return false;
            }
            return true;
        }
        else if(tipoCarta.equals("Defensa")){
            if (topeBat != null) {
                if (topeBat.equals(cartasMap.getCartasCounter().get(carta))) {
                    return true;
                }
                else if(carta.equals("Siga") && cartasMap.getCartasPorTipo().get(topeBat).equals("Defensa")){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(topeDis != null){
                if (topeDis.equals(cartasMap.getCartasCounter().get(carta))) {
                    return true;
            }
            else{
                if (carta.equals("Siga")) {
                    return true;
                }
            }
        }
        }
        else if(tipoCarta.equals("Distancia")){
            int puntos = Integer.parseInt(carta);
            if(topeBat.equals("Siga")){
                if (topeVel!= null && topeVel.equals("Limite de velocidad") && puntos > 51) {
                    return false;
                }
                else{
                    return true;
                }
            }
        }
        return false;
    }
    

    public boolean revisarSeguridad(String carta, List<String> seguridad){
        for (String name : seguridad) {
            if (name.equals(carta)) {
                return true;
            }
        }
        return false;
    }
}
