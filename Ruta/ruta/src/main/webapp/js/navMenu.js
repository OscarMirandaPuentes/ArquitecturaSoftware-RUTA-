document.addEventListener('DOMContentLoaded', function () {
    
    document.getElementById('inicio').addEventListener('click', function () {
        window.location.href ='home.html';
    });

    document.getElementById('partidas').addEventListener('click', function () {
        window.location.href ='games.html';
    });

    document.getElementById('movimientos').addEventListener('click', function () {
        window.location.href ='history.html';
    });

    document.getElementById('perfil').addEventListener('click', function () {
        window.location.href ='userProfile.html';
    });

    document.getElementById('cerrar_sesion').addEventListener('click', function () {
        limpiarCookies();
        window.location.href ='login-register.html';
    });

    document.getElementById('cerrar_sesion2').addEventListener('click', function () {
        limpiarCookies();
        window.location.href ='login-register.html';
    });
});

function limpiarCookies() {
    document.cookie.split(";").forEach(function(c) {
    document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/");
    });
}
