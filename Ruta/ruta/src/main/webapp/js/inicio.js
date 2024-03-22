document.addEventListener('DOMContentLoaded', function () {
    var unirseButton = document.getElementById('unirse');
    
    unirseButton.addEventListener('click', function () {
        // Redirige a la página de login
        window.location.href = 'login.html';
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    
    form.addEventListener('submit', function (event) {
        // Previene el envío del formulario
        event.preventDefault();
        
        // Redirige a la página del tablero
        window.location.href = 'tablero.html';
    });
});
