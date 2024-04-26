var cartaId
var id = localStorage.getItem('id')

document.addEventListener('DOMContentLoaded', function () {
    
    $(document).ready(function(){

        $('.mano a').on('click', function() {
            cartaId = $(this).find('img').attr('id');
        });

        $('#usar').on('click', function() {
            var posCarta = parseInt(cartaId.match(/\d+/)[0]) - 1;
            jugar(id, posCarta, 0)
            $.modal.close();
            alert('Has elegido usar la carta.');
        });

        $('#descartar').on('click', function() {
            var numeroCarta = parseInt(cartaId.match(/\d+/)[0]);
            jugar(id, numeroCarta, 1)
            $.modal.close();
            alert('Has elegido descartar la carta.');
        });

        $('#refrescar').on('click', function() {
            refresh(id)
            //location.reload();
        });

        $('#regresar').on('click', function() {
            window.location.href = 'home.html'
        });
    });


    var nombreJugador = localStorage.getItem('nombreJugador'); // Se obtiene el nombre
    if (nombreJugador) {
        document.getElementById('nombreJ').textContent = nombreJugador;
    }
});

function jugar(id, posCarta, accion) {
    let myData = {
        id: id,
        posCarta: posCarta,
        accion: accion
    };
    $.ajax({
        url: '/api/jugar',
        type: 'POST',
        data: myData,
        success: function (r) {
            console.log(r)
        }
    });
}

function refresh(id) {
    let myData = {
        id: id,
    };
    $.ajax({
        url: '/api/communicate',
        type: 'GET',
        data: myData,
        dataType: 'json',
        success: function (r) {
            console.log(r)
            updateWin(r)
        }
    });
}

function updateWin(datos){
    for (const [carta, valor] of Object.entries(datos)) {
        reemplazarURLCarta(carta, valor); // Llamar a la función reemplazarURLCarta con cada clave y valor
    }
}


