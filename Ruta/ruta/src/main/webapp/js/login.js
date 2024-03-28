var id = document.querySelector('input[type="number"]');


document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        var nombreJugador = document.querySelector('input[type="text"]').value;
        localStorage.setItem('nombreJugador', nombreJugador); // Se almacena en memoria local
        localStorage.setItem('id', id.value); // Se almacena en memoria local
        ingresarPJ(id.value, nombreJugador)
        window.location.href = 'tablero.html';
        
    });

    // Agregar manejador de eventos al botón "Iniciar"
    var btnIniciar = document.getElementById('btn-iniciar');
    btnIniciar.addEventListener('click', function() {
        iniciarJuego();
    });
});

id.addEventListener('input', function() {
    var valor = id.value;
    
    if (valor.includes('e') || valor.includes('+') || valor.includes('-')) {
        id.value = '';
    }
    
});

function ingresarPJ(id, nombre) {
    let myData = {
        id: id,
        nombre: nombre
    };
    $.ajax({
        url: '/ruta/start',
        type: 'POST',
        data: myData,
        success: function (r) {
            console.log("Personaje agregado")
        }
    });
}

function iniciarJuego() {
    // Aquí va la lógica para iniciar el juego
    console.log('El juego ha comenzado');
}