document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        window.location.href = 'tablero.html';
    });
});