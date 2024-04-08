var id = document.querySelector('input[type="number"]');

id.addEventListener('input', function() {
    var valor = id.value;
    
    if (valor.includes('e') || valor.includes('+') || valor.includes('-')) {
        id.value = '';
    }
    
});

document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        // var nombreJugador = 
        localStorage.setItem('id', id.value); // Se almacena en memoria local
        ingresarPJ(id.value)  
    });

    var btnIniciar = document.getElementById('btn-iniciar');
    btnIniciar.addEventListener('click', function() {
        if (id.value != '') {
            iniciarJuego();
            window.location.href = 'tablero.html';
        }
    });
});

function iniciar() {
    $.ajax({
        url: '/ruta/start',
        type: 'GET',
        success: function (r) {
            console.log("Juego iniciado")
        }
    });
}

function ingresarPJ(id) {
    let myData = {
        id: id,
       // nombre: nombre
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
    iniciar()
    console.log('El juego ha comenzado');
}