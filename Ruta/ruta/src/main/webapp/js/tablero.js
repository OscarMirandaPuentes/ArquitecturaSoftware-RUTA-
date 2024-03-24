$(document).ready(function(){
    $('#usar').on('click', function() {
        $.modal.close();
        alert('Has elegido usar la carta.');
        // Acciones
    });

    $('#descartar').on('click', function() {
        $.modal.close();
        alert('Has elegido descartar la carta.');
        //Acciones
    });

    $('#refrescar').on('click', function() {
        //Acciones
        location.reload();
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var nombreJugador = localStorage.getItem('nombreJugador'); // Se obtiene el nombre
    if (nombreJugador) {
        document.getElementById('nombreJ').textContent = nombreJugador;
    }
});