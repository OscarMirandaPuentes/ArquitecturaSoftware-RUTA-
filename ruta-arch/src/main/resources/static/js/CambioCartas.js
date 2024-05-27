const cartas = new Cartas();

const idSeguridadMap1 = {
    "As al volante": "I1E1",
    "Vía libre": "I2E1",
    "Llanta irrompible": "I3E1",
    "Cisterna": "I4E1"
};
const idSeguridadMap2 = {
    "As al volante": "I1E2",
    "Vía libre": "I2E2",
    "Llanta irrompible": "I3E2",
    "Cisterna": "I4E2"
};

function reemplazarURLCarta(identificadorMazo, cartaR) {
    if(identificadorMazo == "Seguridad1"){
        quitarEscalaGrises(idSeguridadMap1[cartaR])
    }
    else if(identificadorMazo == "Seguridad2"){
        quitarEscalaGrises(idSeguridadMap2[cartaR])
    }
    else{
        var ruta = cartas.obtenerRuta(cartaR);
        var elementoCarta = document.getElementById(identificadorMazo);
        if (elementoCarta) {
            elementoCarta.src = ruta;
        }
    }
    
}