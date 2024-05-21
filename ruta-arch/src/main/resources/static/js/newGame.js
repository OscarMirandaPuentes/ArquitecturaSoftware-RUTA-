var input = document.getElementById('numJugadores');

document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        iniciarPartida(input.value)
    });
});

input.addEventListener('input', function() {
    var valor = parseInt(input.value);

    if (valor !== 2 && valor !== 4 && valor !== 6) {
        input.value = '';
    }
});

function iniciarPartida(numPlayers) {
    $.ajax({
        url: '/api/partidas',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(numPlayers),
        success: function (r) {
            localStorage.idPartida = r.id;
            window.location.href = 'enter.html';
        },
        error: function (xhr, status, error) {
            console.error("Error: " + error);
            console.error("Status: " + status);
            console.dir(xhr);
        }
    });
}

