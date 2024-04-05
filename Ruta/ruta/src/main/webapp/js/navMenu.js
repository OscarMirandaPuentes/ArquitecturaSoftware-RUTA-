document.addEventListener('DOMContentLoaded', function () {
    
    document.getElementById('inicio').addEventListener('click', function () {
        window.location.href ='home.html';
    });

    document.getElementById('partidas').addEventListener('click', function () {
        window.location.href ='partidas.html';
    });

    document.getElementById('movimientos').addEventListener('click', function () {
        window.location.href ='movimientos.html';
    });

    document.getElementById('perfil').addEventListener('click', function () {
        window.location.href ='perfil.html';
    });

    document.getElementById('editar_perfil').addEventListener('click', function () {
        window.location.href ='editar_perfil.html';
    });

    document.getElementById('cerrar_sesion').addEventListener('click', function () {
        limpiarCookies();
        window.location.href ='login-register.html';
    });
});

function limpiarCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var nombre = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = nombre + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/";
    }
}
