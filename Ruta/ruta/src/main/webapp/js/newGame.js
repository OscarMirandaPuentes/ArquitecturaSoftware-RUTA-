var input = document.getElementById('numJugadores');

document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        window.location.href = 'enter.html';
    });
});

input.addEventListener('input', function() {
    var valor = parseInt(input.value);

    if (valor !== 2 && valor !== 4 && valor !== 6) {
        input.value = '';
    }
});