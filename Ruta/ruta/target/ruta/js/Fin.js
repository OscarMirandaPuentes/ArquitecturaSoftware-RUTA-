let Equipo = 0;

function actualizarGanador(ganador) {
    Equipo = ganador;
    document.getElementById('winner').textContent = ganador;
}