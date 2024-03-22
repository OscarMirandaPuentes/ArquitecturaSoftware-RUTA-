$(document).ready(function(){
    $('#usar').on('click', function() {
        $.modal.close();
        alert('Has elegido usar la carta.');
        // Acciones
    });

    $('#descartar').on('click', function() {
        $.modal.close();
        alert('Has elegido descartar la carta.');
        //Acciones
    });
});
