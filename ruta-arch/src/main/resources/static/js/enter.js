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
        nombrePj(id)
    });

    var btnIniciar = document.getElementById('btn-iniciar');
    btnIniciar.addEventListener('click', function() {
        if (id.value != '') {
            iniciarJuego();
            iniciar();
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

function ingresarPJ(id, nombre) {
    let myData = {
        id: id,
        nombre: nombre
    };
    
    // Obtener el valor del email de la cookie
    var email = document.cookie.replace(/(?:(?:^|.*;\s*)email\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    
        // Agregar la cookie al encabezado de la solicitud
        $.ajax({
            url: '/ruta/start',
            type: 'POST',
            data: myData,
            headers: {
                'Cookie': 'email=' + email 
            },
            success: function (r) {
                console.log("Personaje agregado")
            }
        });
    } 

    function nombrePj(id) {
        let myData = {
            action: "EMAIL"
        };
        var email = document.cookie.replace(/(?:(?:^|.*;\s*)email\s*\=\s*([^;]*).*$)|^.*$/, "$1");
        
            // Agregar la cookie al encabezado de la solicitud
            $.ajax({
                url: '/ruta/info',
                type: 'POST',
                data: myData,
                headers: {
                    'Cookie': 'email=' + email 
                },
                success: function (r) {
                    ingresarPJ(id, r)
                }
            });
        } 

        
function iniciarJuego() {
    iniciar()
    console.log('El juego ha comenzado');
}