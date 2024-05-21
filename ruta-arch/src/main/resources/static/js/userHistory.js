var datos = [
    {
        numero: '01',
        idPartida: '123',
        carta: 'Sin gasolina',
        descripcion: 'Usada'
    },
    {
        numero: '02',
        idPartida: '456',
        carta: 'Carta 2',
        descripcion: 'Descripción 2'
    }
];

function obtenerDatosPartidas() {
    //TODO:Historial
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
                         '<td>' + partida.carta + '</td>' +
                         '<td>' + partida.descripcion + '</td>';
        cuerpoTabla.appendChild(fila);
    });
}

agregarDatosTabla(datos) //Datos prueba
window.onload = function() {
    obtenerDatosPartidas();
};
