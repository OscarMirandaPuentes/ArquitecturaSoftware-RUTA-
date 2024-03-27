var cartaId
var id = localStorage.getItem('id')

document.addEventListener('DOMContentLoaded', function () {
    
    $(document).ready(function(){

        $('.mano a').on('click', function() {
            cartaId = $(this).find('img').attr('id');
        });

        $('#usar').on('click', function() {
            var posCarta = parseInt(cartaId.match(/\d+/)[0]);
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
            //Acciones
            location.reload();
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
            console(r)
        }
    });
}
