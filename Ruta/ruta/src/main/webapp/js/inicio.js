var input = document.getElementById('numJugadores');

document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        window.location.href = 'login.html';
    });
});

input.addEventListener('input', function() {
    // Obtener el valor ingresado por el usuario
    var valor = parseInt(input.value);

    // Validar si el valor es 2, 4 o 6
    if (valor !== 2 && valor !== 4 && valor !== 6) {
        // Si no es ninguno de los valores permitidos, restablecer el valor a vacío
        input.value = '';
    }
});