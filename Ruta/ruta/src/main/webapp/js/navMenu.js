document.addEventListener('DOMContentLoaded', function () {
    // Agregar listeners a los enlaces del menú de navegación
    document.getElementById('inicio').addEventListener('click', function () {
        navigateTo('home.html');
    });

    document.getElementById('partidas').addEventListener('click', function () {
        navigateTo('partidas.html');
    });

    document.getElementById('movimientos').addEventListener('click', function () {
        navigateTo('movimientos.html');
    });

    document.getElementById('perfil').addEventListener('click', function () {
        navigateTo('perfil.html');
    });

    document.getElementById('editar_perfil').addEventListener('click', function () {
        navigateTo('editar_perfil.html');
    });

    document.getElementById('cerrar_sesion').addEventListener('click', function () {
        limpiarCookies();
        navigateTo('login-register.html');
    });
});

function navigateTo(url) {
    window.location.href = url;
}

function limpiarCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var nombre = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = nombre + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/";
    }
}
