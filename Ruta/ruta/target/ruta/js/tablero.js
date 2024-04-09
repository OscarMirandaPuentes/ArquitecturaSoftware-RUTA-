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
        url: '/ruta/jugar',
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
        url: '/ruta/communicate',
        type: 'GET',
        data: myData,
        success: function (r) {
            updateWin(r)
        }
    });
}

function updateWin(array){
    if (array.length === 6) {
        reemplazarURLCarta("PBatallaE1", array[0]);
        reemplazarURLCarta("PDistaciaE1", array[1]);
        reemplazarURLCarta("PVelocidadE1", array[2]);
        reemplazarURLCarta("PBatallaE2", array[3]);
        reemplazarURLCarta("PDistaciaE2", array[4]);
        reemplazarURLCarta("PVelocidadE2", array[5]);
    }
    
    if (array.length === 7) {
        reemplazarURLCarta("Carta 1", array[0]);
        reemplazarURLCarta("Carta 2", array[1]);
        reemplazarURLCarta("Carta 3", array[2]);
        reemplazarURLCarta("Carta 4", array[3]);
        reemplazarURLCarta("Carta 5", array[4]);
        reemplazarURLCarta("Carta 6", array[5]);
        reemplazarURLCarta("Carta 7", array[6]);
    }
    
    if (array.length === 13) {
        reemplazarURLCarta("PBatallaE1", array[0]);
        reemplazarURLCarta("PDistaciaE1", array[1]);
        reemplazarURLCarta("PVelocidadE1", array[2]);
        reemplazarURLCarta("PBatallaE2", array[3]);
        reemplazarURLCarta("PDistaciaE2", array[4]);
        reemplazarURLCarta("PVelocidadE2", array[5]);
        reemplazarURLCarta("Carta 1", array[6]);
        reemplazarURLCarta("Carta 2", array[7]);
        reemplazarURLCarta("Carta 3", array[8]);
        reemplazarURLCarta("Carta 4", array[9]);
        reemplazarURLCarta("Carta 5", array[10]);
        reemplazarURLCarta("Carta 6", array[11]);
        reemplazarURLCarta("Carta 7", array[12]);
    }
}

