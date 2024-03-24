document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        var nombreJugador = document.querySelector('input[type="text"]').value;
        localStorage.setItem('nombreJugador', nombreJugador); // Se almacena en memoria local
        window.location.href = 'tablero.html';
        
    });
});