var id = document.querySelector('input[type="number"]');

id.addEventListener('input', function() {
    var valor = id.value;
    
    if (valor.includes('e') || valor.includes('+') || valor.includes('-')) {
        id.value = '';
    }
    
});

document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        //localStorage.setItem('id', id.value); // Se almacena en memoria local

        if (id.value != '') {
            window.location.href = 'enter.html';
            //window.location.href = 'tablero.html';
        }
    });
});
