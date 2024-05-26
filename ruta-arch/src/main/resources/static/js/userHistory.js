function obtenerDatosPartidas(email) {
    $.ajax({
        url: "/api/user/historial", // URL de tu solicitud AJAX
        type: 'POST', // Método de la solicitud (GET, POST, etc.)
        contentType: 'text/plain', // Especifica el tipo de contenido como texto
        data: email, // Envía el email como texto
        dataType: 'json', 
        success: function(response) {
            agregarDatosTabla(response);
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
        fila.innerHTML = '<td>' + partida.id + '</td>' +
                         '<td>' + partida.idPartida + '</td>' +
                         '<td>' + partida.carta + '</td>' +
                         '<td>' + partida.descripcion + '</td>';
        cuerpoTabla.appendChild(fila);
    });
}

// Función para agregar datos a la tabla
function agregarDatosTabla(datosPartidas) {
    var cuerpoTabla = document.getElementById('cuerpoTabla');
    cuerpoTabla.innerHTML = '';

    datosPartidas.forEach(function(partida, index) {
        var fila = document.createElement('tr');
        fila.innerHTML = '<td>' + partida.id + '</td>' +
                         '<td>' + partida.jugadorTurno + '</td>' +
                         '<td>' + partida.estado + '</td>' +
        cuerpoTabla.appendChild(fila);
    });
}

window.onload = function() {
    obtenerDatosPartidas(localStorage.email);
};
