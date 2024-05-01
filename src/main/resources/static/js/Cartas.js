class Cartas {
    constructor() {
        this.imagenesCartas = {
            "Sin gasolina": "../images/Cartas/3.png",
            "Pinchazo": "../images/Cartas/1.png",
            "Accidente": "../images/Cartas/5.png",
            "Límite de velocidad": "../images/Cartas/7.png",
            "Pare": "../images/Cartas/9.png",
            "Gasolina": "../images/Cartas/4.png",
            "Llanta de repuesto": "../images/Cartas/2.png",
            "Reparación": "../images/Cartas/6.png",
            "Fin de límite": "../images/Cartas/8.png",
            "Siga": "../images/Cartas/10.png",
            "Cisterna": "../images/Cartas/16.png",
            "Llanta irrompible": "../images/Cartas/17.png",
            "As al volante": "../images/Cartas/18.png",
            "Vía libre": "../images/Cartas/19.png",
            "200": "../images/Cartas/11.png",
            "100": "../images/Cartas/12.png",
            "75": "../images/Cartas/13.png",
            "50": "../images/Cartas/14.png",
            "25": "../images/Cartas/15.png"
        };
    }

    obtenerRuta(nombreCarta) {
        return this.imagenesCartas[nombreCarta] || "../images/Cartas/20.png";
    }
}
