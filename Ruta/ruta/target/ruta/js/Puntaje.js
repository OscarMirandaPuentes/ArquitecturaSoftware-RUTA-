let puntajeEquipo1 = 0;
let puntajeEquipo2 = 0;

function actualizarPuntajeEquipo1(puntaje) {
    puntajeEquipo1 = puntaje;
    document.getElementById('puntajeEquipo1').textContent = puntaje;
}

function actualizarPuntajeEquipo2(puntaje) {
    puntajeEquipo2 = puntaje;
    document.getElementById('puntajeEquipo2').textContent = puntaje;
}

//actualizarPuntajeEquipo1(10);
//actualizarPuntajeEquipo2(5);