var datos = [
    {
        numero: '01',
        idPartida: '123',
        fecha_i: '01/01/2024',
        fecha_f: '01/01/2024'
    },
    {
        numero: '02',
        idPartida: '456',
        fecha_i: '04/11/2024',
        fecha_f: ''
    }
];

function obtenerDatosPartidas() {
    $.ajax({
        url: 'tu_url_de_solicitud_ajax', // URL de tu solicitud AJAX
        type: 'GET', // Método de la solicitud (GET, POST, etc.)
        dataType: 'json', 
        success: function(response) {
            if (response.success) {
                agregarDatosTabla(response.data);
            } else {
                console.error('Error en la solicitud AJAX');
            }
        },
        error: function(xhr, status, error) {
            console.error('Error en la solicitud AJAX:', error);
        }
    });
}

function agregarDatosTabla(datosPartidas) {
    var cuerpoTabla = document.getElementById('cuerpoTabla');

    cuerpoTabla.innerHTML = '';

    datosPartidas.forEach(function(partida) {
        var fila = document.createElement('tr');
        fila.innerHTML = '<td>' + partida.numero + '</td>' +
                         '<td>' + partida.idPartida + '</td>' +
                         '<td>' + partida.fecha_i + '</td>' +
                         '<td>' + partida.fecha_f + '</td>';
        cuerpoTabla.appendChild(fila);
    });
}

agregarDatosTabla(datos) //Datos prueba

window.onload = function() {
    obtenerDatosPartidas();
};

function redirigirAHistorial(idPartida) {
    var urlHistorial = 'movements.html?id=' + idPartida;
    window.location.href = urlHistorial;
}

document.addEventListener('DOMContentLoaded', function() {
    $('#cuerpoTabla').on('click', 'tr', function() {
        console.log('H');
        var idPartida = $(this).find('td:eq(1)').text();
        redirigirAHistorial(idPartida);
    });
});
