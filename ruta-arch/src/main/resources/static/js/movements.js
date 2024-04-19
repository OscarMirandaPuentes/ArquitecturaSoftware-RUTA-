

function obtenerIdPartidaDeUrl() {
    var urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}

// Función para cargar los movimientos de la partida
function cargarMovimientosDePartida(idPartida) {
    $.ajax({
        url: 'tu_url_de_solicitud_ajax_para_movimientos?id=' + idPartida, // URL de tu solicitud AJAX para obtener los movimientos
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            if (response.success) {
                agregarDatosTabla(response.data);
            } else {
                console.error('Error en la solicitud AJAX para obtener movimientos');
            }
        },
        error: function(xhr, status, error) {
            console.error('Error en la solicitud AJAX para obtener movimientos:', error);
        }
    });
}

function agregarDatosTabla(datosPartidas) {
    var cuerpoTabla = document.getElementById('cuerpoTabla');

    cuerpoTabla.innerHTML = '';

    datosPartidas.forEach(function(partida) {
        var fila = document.createElement('tr');
        fila.innerHTML = '<td>' + partida.numero + '</td>' +
                         '<td>' + partida.idJugador + '</td>' +
                         '<td>' + partida.Carta + '</td>' +
                         '<td>' + partida.Descrpción + '</td>';
        cuerpoTabla.appendChild(fila);
    });
}

document.addEventListener('DOMContentLoaded', function() {
    var idPartida = obtenerIdPartidaDeUrl();
    if (idPartida) {
        cargarMovimientosDePartida(idPartida);
    } else {
        console.error('ID de partida no proporcionado en la URL');
    }
});

var datos = [
    {
        numero: '01',
        idJugador: '001',
        Carta: 'Sin gasolina',
        Descripción: 'Usada'
    },
    {
        numero: '02',
        idJugador: '002',
        Carta: 'Carta 2',
        Descripción: 'Descripción 2'
    }
];
agregarDatosTabla(datos)